#### Flow pattern

In this repository I would like to present flow pattern.

Let's start with an example. Assume that we have an air conditioner class that handle 
two devices(heater and cooler) and sensors with this flow:

<img alt="flowchart for flow pattern" src="https://github.com/imohsenb/FlowPattern/blob/master/flowchart.png" height="400"/>

####Simple Way
We can simply implement this signatures for the flow chart:
```kotlin
class AirConditioner {
    fun start(){}
    private fun startingSystem(){}
    private fun checkSystem(){}
    private fun checkTemperature(){}
    private fun turnOffDevices(){}
    private fun heating(){}
    private fun startHeater(){}
    private fun cooling(){}
    private fun startCooler(){}
    private fun systemError(msg: String){}
}
``` 

As you can see, there are lot of states in this class and maybe they call each other for multiple time.
Full source code available [Here](https://github.com/imohsenb/FlowPattern/tree/290afc56ac6a608222ad5473dd24f8b575fb1a87)

But imagine that each method has many lines of code to handle a state.
Our class becomes a giant! developing new feature and maintaining functionality become 
harder and harder.

So, let's break these states into another classes then we make good implementation of 
SRP (Single Responsibility Principle) and life becomes easier.

#### Advanced Way
First of all, Create an flow activity interface
```kotlin
interface FlowActivity<T: FlowContext>{
    fun run(context : T) : Single<FlowActivity<*>>
}
```