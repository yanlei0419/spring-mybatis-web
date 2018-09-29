@echo off
echo ====================================
%~d0
set current_path=%~d0
echo current_path:%current_path%
cd %current_path%
cd ..
call svn cleanup
call svn up
call mvn clean
call mvn install -Psqlite -Dmaven.test.skip=true
echo =====================================
pause