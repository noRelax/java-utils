@echo off
setlocal EnableDelayedExpansion 
set a=1
set b=0
for /f "delims=" %%i in ('dir /b /s *.jpg') do (
if not "%%~ni"=="%~n0" (
if !b!==0 (ren "%%i" "!a!_1%%~xi" 
set /a b+=1)
if !b!==1 (ren "%%i" "!a!%%~xi" 
set /a b+=1)
if !b!==2 (ren "%%i" "!a!_m%%~xi" 
set /a b=0)
set /a a+=1 
) 
) 
echo 批量重命名完成！ 
pause&exit
