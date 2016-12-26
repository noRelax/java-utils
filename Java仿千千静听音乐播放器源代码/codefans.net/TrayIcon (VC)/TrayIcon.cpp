#include <windows.h>
#include <process.h>
#include <jni.h>
#include "TrayIcon.h" 

#define MYWM_NOTIFYICON		(WM_APP+100)
#define WNDNAME_MAX         20
#define MY_ICON_ID			333
class IconData {
private:
	HBITMAP hBitmapXOR;
	HBITMAP	hBitmapAND;
	int wd, hi;
public:
	IconData();
	~IconData();
	int setData(unsigned long *data, int wd, int hi);
	HICON makeIcon(HINSTANCE hInst);
};	  

typedef struct {
	BOOL visible;
	IconData *icon;
	char *tooltip;
	jobject globalClass;
} TrayIconData;

#define MOUSE_BTN_UP        1
#define MOUSE_BTN_DOUBLE    2	 

HINSTANCE g_hinst = NULL;
HWND my_hDlg = NULL;
CHAR szAppName[] = "QSJavaTray";
CHAR szWndName[WNDNAME_MAX+1];
TrayIconData tray_icon;
HANDLE wait_event = NULL;
HANDLE exit_event = NULL;
JavaVM *hJavaVM = NULL;
UINT g_TaskbarRestart = 0;
BOOL TrayMessage(HWND hDlg, DWORD dwMessage, HICON hIcon, PSTR pszTip);
HWND MakeHWND(HINSTANCE hInst);
void showTrayIcon();
void RemoveHWND();
void DialogThread( void *dummy );
void freeIcon(JNIEnv *env);
void cleanUpExit(JNIEnv *env);
void updateIcon();
void makeInvisible();
void doMouseEvent(JNIEnv * env,jobject jThis,int button,int x,int y);


JNIEnv *jniEnv_current;
jobject jobject_current;

class ThreadJavaCallback {
public:
    virtual int execute(JNIEnv* env) = 0;
};
class MouseJavaCallback : public ThreadJavaCallback {
protected:
	int m_Button;
	int m_X;
	int m_Y;
public:
    MouseJavaCallback(int button,int x,int y);    
    virtual int execute(JNIEnv* env);    
};


BOOL WINAPI DllMain(HANDLE hInst, ULONG fdwReason, LPVOID lpReserved) {
    switch(fdwReason) {
        case DLL_PROCESS_ATTACH:			
			g_hinst = (HINSTANCE)hInst;
			break;
		case DLL_THREAD_ATTACH:
		case DLL_THREAD_DETACH:
			break;
		case DLL_PROCESS_DETACH:
			break;
    }
    return TRUE;
}



MouseJavaCallback::MouseJavaCallback(int button, int x,int y) {
    m_Button = button; 
	m_X = x;
	m_Y = y;
}
int MouseJavaCallback::execute(JNIEnv* env) {
	jobject obj = tray_icon.globalClass;
	if (obj == 0) return -1;
	jclass winTrayClass = env->GetObjectClass(obj);
	if (winTrayClass == 0) return -1;
	jmethodID mid = env->GetMethodID(winTrayClass, "trayIconCallback", "(III)V");
	if (mid == 0) return -1;
	env->CallVoidMethod(obj, mid, m_Button, m_X, m_Y);
	return 0;
}


void CallJavaVM(JavaVM* vm, ThreadJavaCallback* call) {
	JNIEnv* env;
	if (vm->AttachCurrentThread((void**) &env, NULL) < 0) return ;
	call->execute(env);	
	if (env->ExceptionOccurred()) env->ExceptionDescribe();
	vm->DetachCurrentThread();
}

void CallJavaThread(void *arg) {
	ThreadJavaCallback *tjc = (ThreadJavaCallback*)arg;
	CallJavaVM(hJavaVM, tjc);
	delete tjc;
}
void CallJavaVMSThread(ThreadJavaCallback* tjc) {
	if (_beginthread(CallJavaThread, 0, tjc) == -1) delete tjc;
}

void cleanUpExit(JNIEnv *env) {
	freeIcon(env);
	RemoveHWND();
}

void freeIcon(JNIEnv *env) {
	if (tray_icon.visible == TRUE) {
		makeInvisible();
	}
	tray_icon.visible = FALSE;
	if (tray_icon.icon != NULL) {
		delete tray_icon.icon;
		tray_icon.icon = NULL;
	}
	if (tray_icon.tooltip != NULL) {
		delete tray_icon.tooltip;
		tray_icon.tooltip = NULL;
	}
	if (tray_icon.globalClass != 0) {
		if (env != NULL) {
			env->DeleteGlobalRef(tray_icon.globalClass);
		} else {
        }
		tray_icon.globalClass = 0;
	}
}

void updateIcon() {
	if (my_hDlg != NULL && tray_icon.visible == TRUE) {		
		if (g_hinst != NULL && tray_icon.icon != NULL) {
			HICON icon = tray_icon.icon->makeIcon(g_hinst);
			if (icon != NULL) {
				TrayMessage(my_hDlg, NIM_MODIFY,  icon, tray_icon.tooltip);
			}
		} else {
			makeInvisible();
		}
	}
}
void makeInvisible() {
	if (tray_icon.visible == TRUE) {
		if (my_hDlg != NULL) TrayMessage(my_hDlg, NIM_DELETE,  NULL, NULL);
		tray_icon.visible = FALSE;
	}
}

BOOL TrayMessage(HWND hDlg, DWORD dwMessage, HICON hIcon, PSTR pszTip) {
	BOOL res;
	NOTIFYICONDATA tnd;
	tnd.cbSize		= sizeof(NOTIFYICONDATA);
	tnd.hWnd		= hDlg;
	tnd.uID			= MY_ICON_ID;
	tnd.uFlags		= NIF_MESSAGE | NIF_ICON | NIF_TIP;
	tnd.uCallbackMessage	= MYWM_NOTIFYICON;
	tnd.hIcon		= hIcon;
	if (pszTip) {
		lstrcpyn(tnd.szTip, pszTip, sizeof(tnd.szTip));
	} else {
		tnd.szTip[0] = '\0';
	}
	res = Shell_NotifyIcon(dwMessage, &tnd);
	if (hIcon) DestroyIcon(hIcon);
	return res;
}							

void HandleNotifyIcon(WPARAM id_num, LPARAM lParam) {	
	POINT mouse_pos;
	GetCursorPos(&mouse_pos);
    int button = 0;
    switch (lParam)	{
		case WM_LBUTTONDOWN:button = 1; break;
		case WM_LBUTTONUP:button = 2; break;
		case WM_RBUTTONDOWN:button = 3; break;
		case WM_RBUTTONUP:button = 4; break;
		case WM_MBUTTONDOWN:button = 5; break;
		case WM_MBUTTONUP:button = 6; break;
		case WM_LBUTTONDBLCLK:button = 7; break;
    }
	MouseJavaCallback* call = new MouseJavaCallback(button,mouse_pos.x,mouse_pos.y);
	CallJavaVMSThread(call);
}


LONG APIENTRY WndProc(HWND hWnd, UINT iMessage, UINT wParam, LONG lParam) {
	switch (iMessage) {
		case WM_DESTROY:
			PostQuitMessage(0) ;
			break;
		case MYWM_NOTIFYICON:
			HandleNotifyIcon(wParam, lParam);
			break;		
		case WM_CREATE:
            g_TaskbarRestart = RegisterWindowMessage(TEXT("TaskbarCreated"));
            break;
		case WM_GETMINMAXINFO:
		case WM_PAINT:
		case WM_MOUSEMOVE:
		case WM_RBUTTONDOWN:
		case WM_RBUTTONUP:
		case WM_MOVE:
		case WM_SIZE:
		case WM_LBUTTONDOWN:
		case WM_KEYDOWN:
			break;
		default:
            if (iMessage == g_TaskbarRestart) {
				if (tray_icon.visible == TRUE) {
					   showTrayIcon();
				}
			}
            else return DefWindowProc (hWnd, iMessage, wParam, lParam) ;
	}
	return 0L ;
}



HWND MakeHWND(HINSTANCE hInst) {
	HWND hWnd;
	WNDCLASS wndclass;
	wndclass.style         = CS_HREDRAW | CS_VREDRAW;
	wndclass.lpfnWndProc   = (WNDPROC) WndProc;
	wndclass.cbClsExtra    = 0;
	wndclass.cbWndExtra    = 0;
	wndclass.hInstance     = hInst;
	wndclass.hIcon         = LoadIcon(hInst, IDI_APPLICATION);
	wndclass.hCursor       = LoadCursor(NULL, IDC_ARROW);
	wndclass.hbrBackground = (HBRUSH)GetStockObject(BLACK_BRUSH);
	wndclass.lpszMenuName  = NULL;
	wndclass.lpszClassName = szAppName;
	if (!RegisterClass(&wndclass)) return NULL;
	hWnd = CreateWindow(szAppName,szWndName,WS_OVERLAPPEDWINDOW,0,0,100,100,NULL,NULL, hInst, NULL);
	return hWnd;
}

void RemoveHWND() {
	if (my_hDlg != NULL) {
		exit_event = CreateEvent(NULL,FALSE,FALSE,NULL);
		PostMessage(my_hDlg, WM_NCDESTROY, 0, 0);
		PostMessage(my_hDlg, WM_DESTROY, 0, 0);
		WaitForSingleObject(exit_event,10000);
		CloseHandle(exit_event);
		exit_event = NULL;
	}
	if (wait_event != NULL) {
		CloseHandle(wait_event);
		wait_event = NULL;
	}
}

void DialogThread(void *dummy) {
	MSG msg;
	my_hDlg = MakeHWND(g_hinst);
	if (wait_event != NULL) SetEvent(wait_event);
	ShowWindow(my_hDlg, SW_HIDE);
	UpdateWindow(my_hDlg);
	while (GetMessage(&msg, NULL, 0, 0)){
		TranslateMessage(&msg) ;
		DispatchMessage(&msg) ;
	}
	UnregisterClass(szAppName, g_hinst);
	if (exit_event != NULL) SetEvent(exit_event);
}




IconData::IconData() {
	wd = hi = 0;
	hBitmapAND = hBitmapXOR = NULL;
}

IconData::~IconData() {
	if (hBitmapAND != NULL) DeleteObject(hBitmapAND);
	if (hBitmapXOR != NULL) DeleteObject(hBitmapXOR);
}

HICON IconData::makeIcon(HINSTANCE hInst) {
        ICONINFO ii;
        ii.fIcon    = TRUE;
        ii.xHotspot = 0;
        ii.yHotspot = 0;
        ii.hbmMask  = hBitmapAND;
        ii.hbmColor = hBitmapXOR;
        return CreateIconIndirect(&ii);
}

int IconData::setData(unsigned long *data, int wd, int hi) {
	int res = -1;
	this->wd = wd;
	this->hi = hi;
	if (hBitmapAND != NULL) DeleteObject(hBitmapAND);
	if (hBitmapXOR != NULL) DeleteObject(hBitmapXOR);
	if (wd > 0 && hi > 0) {
	    	BITMAPINFOHEADER bih;
		    bih.biSize          = sizeof(BITMAPINFOHEADER);
	    	bih.biWidth         = wd;
		    bih.biHeight        = hi;
		    bih.biPlanes        = 1;
	    	bih.biBitCount      = 24;
	    	bih.biCompression   = BI_RGB;
	    	bih.biSizeImage     = 0;
	    	bih.biXPelsPerMeter = 0;
	    	bih.biYPelsPerMeter = 0;
	    	bih.biClrUsed       = 0;
	    	bih.biClrImportant  = 0;
	    	HDC hdc = CreateCompatibleDC(NULL);
	    	hBitmapXOR = CreateDIBSection(hdc, (LPBITMAPINFO)&bih, DIB_RGB_COLORS, (LPVOID *)NULL, NULL, 0);
	    	SelectObject(hdc, hBitmapXOR);
		    long size = (wd*hi/8)+1;
	    	unsigned char *andMask = new unsigned char[size];
		    if (andMask != NULL) {
			    for (int i = 0; i < size; i++) andMask[i] = 0;
	    	    	unsigned long pixel;
		        	unsigned char red, green, blue, alpha;
		        	for (int row = 0; row < hi; row++) {
    			    	for (int col = 0; col < wd; col++) {
	            			pixel = data[(row*wd)+col];
		            		alpha = (unsigned char)((pixel >> 24) & 0x000000ff);
		            		red   = (unsigned char)((pixel >> 16) & 0x000000ff);
	                    	green = (unsigned char)((pixel >>  8) & 0x000000ff);
	                    	blue  = (unsigned char)( pixel        & 0x000000ff);
	        		    	if (alpha == 0xFF) {
						        SetPixel(hdc, col, row, RGB(red, green, blue));
					        } else {
		        			    int p = (row*wd) + col;
				        	    andMask[p/8] |= 1 << (7-(p%8));
						        SetPixel(hdc, col, row, RGB(0, 0, 0));
					    }
				    }
			    }
			    hBitmapAND = CreateBitmap(wd, hi, 1, 1, andMask);
			    delete andMask;
			    res = 0;
		    }
		    DeleteDC(hdc);
	}
	return res;
}





JNIEXPORT void JNICALL Java_eb_cstop_swt_TrayIcon_initTrayIcon(JNIEnv * env, jobject jThis, jobject ic_cls,jstring wndName, jint){	
	jniEnv_current = env;
	jobject_current = jThis;
	jobject globalWinTrayClass = env->NewGlobalRef(ic_cls);
	if (globalWinTrayClass != 0) {
		if (tray_icon.globalClass != 0)
			env->DeleteGlobalRef(tray_icon.globalClass);
		tray_icon.globalClass = globalWinTrayClass;
	}

    env->GetJavaVM(&hJavaVM);
	if (my_hDlg == NULL) {
		const char *cWndName = env->GetStringUTFChars(wndName, 0);
		strncpy(szWndName ,cWndName, WNDNAME_MAX);
		szWndName[WNDNAME_MAX] = 0;
		env->ReleaseStringUTFChars(wndName, cWndName);		
		if (g_hinst != NULL) {
			wait_event = CreateEvent(NULL,FALSE,FALSE,NULL);
			_beginthread(DialogThread, 0, NULL );
		}
	}

}

JNIEXPORT void JNICALL Java_eb_cstop_swt_TrayIcon_setTrayIconTip(JNIEnv *env, jobject, jstring tip){	
	const char *tooltip = env->GetStringUTFChars(tip, 0);
	int len = strlen(tooltip);
	if (tray_icon.tooltip != NULL)	
		delete tray_icon.tooltip;
	tray_icon.tooltip = new char[len+1];
	strcpy(tray_icon.tooltip, tooltip);
	env->ReleaseStringUTFChars(tip, tooltip);
	updateIcon();
}

JNIEXPORT void JNICALL Java_eb_cstop_swt_TrayIcon_setTrayIconData(JNIEnv *env, jobject, jint wd, jint hi, jintArray array){	
	if (g_hinst == NULL) {
		return;
	}
	jsize len = env->GetArrayLength(array);
	jint *body = env->GetIntArrayElements(array, 0);
	IconData *data = new IconData();
	if (data != NULL && data->setData((unsigned long *)body, wd, hi) != -1) {
		IconData *olddata = tray_icon.icon;
		tray_icon.icon = data;
		updateIcon();
		if (olddata != NULL) delete olddata;
	} else {
		delete data;
	}
	env->ReleaseIntArrayElements(array, body, 0);
}

JNIEXPORT void JNICALL Java_eb_cstop_swt_TrayIcon_show(JNIEnv * env, jobject){	
	showTrayIcon();
}

JNIEXPORT void JNICALL Java_eb_cstop_swt_TrayIcon_close(JNIEnv * env, jobject){	
	makeInvisible();
}					 

void showTrayIcon() {
	if (tray_icon.visible == FALSE) {
		if (tray_icon.icon != NULL) {
			HICON icon = tray_icon.icon->makeIcon(g_hinst);
			if (icon != NULL) {
				TrayMessage(my_hDlg, NIM_ADD,icon, tray_icon.tooltip);
				tray_icon.visible = TRUE;
			}
		}
	}
}