@echo off
rem conecta el puerto 3000 del emulador con el de la PC
D:\Android\SDK\platform-tools\adb.exe reverse tcp:3000 tcp:3000
rem inicia el backend
cd /d "%~dp0"
npm start
