ps aux | grep web
PID=$(ps aux | grep web)
kill -9 $(PID)
ps aux | grep web