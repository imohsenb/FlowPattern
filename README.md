#### Flow pattern

In this repository I would like to present flow pattern.

Let's start with an example. Assume that we have an air conditioner class that handle 
two devices(heater and cooler) and sensors with this flow:

<img alt="flowchart for flow pattern" src="https://github.com/imohsenb/FlowPattern/blob/master/flowchart.png" height="400"/>

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
