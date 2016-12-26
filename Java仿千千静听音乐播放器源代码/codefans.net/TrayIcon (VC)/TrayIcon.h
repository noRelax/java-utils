#include <jni.h>

#ifndef _Included_eb_cstop_swt_TrayIcon
#define _Included_eb_cstop_swt_TrayIcon
#ifdef __cplusplus
extern "C" {
#endif
JNIEXPORT void JNICALL Java_eb_cstop_swt_TrayIcon_initTrayIcon(JNIEnv *, jobject, jobject, jstring, jint);
JNIEXPORT void JNICALL Java_eb_cstop_swt_TrayIcon_setTrayIconTip(JNIEnv *, jobject, jstring);
JNIEXPORT void JNICALL Java_eb_cstop_swt_TrayIcon_setTrayIconData(JNIEnv *, jobject, jint, jint, jintArray);
JNIEXPORT void JNICALL Java_eb_cstop_swt_TrayIcon_show(JNIEnv *, jobject);
JNIEXPORT void JNICALL Java_eb_cstop_swt_TrayIcon_close(JNIEnv *, jobject);
#ifdef __cplusplus
}
#endif
#endif
