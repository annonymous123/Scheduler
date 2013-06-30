Scheduler
=========
TimeSetter is the main class.

It will create a thread CallSetter every two hour.

Call Setter will then create thread Caller and Messager which will call and send sms respectively to patient whose schedule
time falls between that duration(two hours).
