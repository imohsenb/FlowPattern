### Flow pattern

In this repository I would like to present flow pattern. this pattern maintain about
`Step` (may not `State`) and not only concrete next and previous step and this 
solution can control flow decisions and transition them to next step of the
flowchart after performing special action.

Let's start with an example. Assume that we have an air conditioner class that handle 
two devices(heater and cooler) and sensors with this flow:

<img alt="flowchart for flow pattern" src="https://github.com/imohsenb/FlowPattern/blob/master/flowchart.png" height="400"/>

#### Simple Way
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

As you can see, there are lot of methods (states) in this class and maybe they call
each other for multiple time to performing flow.

Please look at full source code of simple way that available [Here](https://github.com/imohsenb/FlowPattern/tree/290afc56ac6a608222ad5473dd24f8b575fb1a87)

But imagine that each method has many lines of code to handle a state and handing some actions.
So, our class becomes a giant class with many line of code and various task and 
responsibility to do. developing new features and maintaining functionality become 
harder and harder.

So, let's break these states into another classes then we make good implementation of 
SRP (Single Responsibility Principle) and life becomes easier.

We called each class( that handle special step) `FlowActivity`. it has `run` method 
and simply it can return another `Activity`

```kotlin
interface FlowActivity {
    fun run() : FlowActivity
}
```

let's create an activity for `checkSystem` process:

```kotlin
class CheckSystemFlowActivity: FlowActivity {
    override fun run() {
        return when {
            isReady -> CheckTemperatureFlowActivity()
            errorExits -> ErrorFlowActivity()
            else -> StartingSystemFlowActivity()
        }
    }
}
```

#### Advanced Way

Last implementation of `FlowActivity` is only good for synchronize flows and it's to 
hard to handle asynchronous process. So we change return type of `run` method in 
`io.reactivex.Single` and it makes `FlowActivity` more flexible and now, we able to 
handle asynchronous process too. Even we can handle `scheduler` of process.
```kotlin
interface FlowActivity{
    fun run() : Single<FlowActivity>
}
```

```kotlin
class CheckSystemFlowActivity : FlowActivity {
    fun run(): Single<FlowActivity> {
        return Single.create{ flow -> 
            when {
                isReady -> flow.onSuccess(CheckTemperatureFlowActivity())
                errorExits -> flow.onError(Exception("unable starting system"))
                else -> flow.onSuccess(StartingSystemFlowActivity())
            }
        }
    }
}
```

#### Context
Another change we should do is about sharing data and state between steps (implementations of flow FlowActivity)
```kotlin
interface FlowContext 
```
this is maker interface to generify FlowActivity then we should refactor `FlowActivity`
to use `FlowContext`
```kotlin
interface FlowActivity<T: FlowContext>{
    fun run(context: T) : Single<FlowActivity<*>>
}
```
now we create `AcFlowContext` for example:
```kotlin
class AcFlowContext : FlowContext {
    var isReady = false
    var errorExits = false
}
```
finally we refactor `checkSystem` activity

```kotlin
class CheckSystemAcFlowActivity : FlowActivity<AcFlowContext> {
    override fun run(context: AcFlowContext): Single<FlowActivity<*>> {
        return Single.create { flow ->
            context.apply {
                when {
                    isReady -> flow.onSuccess(CheckTemperatureAcFlowActivity())
                    errorExits -> flow.onError(Exception("unable starting system"))
                    else -> flow.onSuccess(StartingSystemAcFlowActivity())
                }
            }
        }
    }
}
```

#### Execution
Now, it's time to executing our flow for both simple way and advance way:

##### executing "Simple way"
Simply we can use a `while` loop to executing flows one after another until flow
goes `null`.
```kotlin

fun start() {
    var flow = StartFlowActivity()
    
    while(flow != null) {
        flow = flow.run(context)
    }
}

```
##### executing "Advanced way"

```kotlin
fun start() {
    val flow = StartAcFlowActivity()
    execute(flow, context)
}
```
```kotlin
fun execute(flow: FlowActivity<AcFlowContext>, context: AcFlowContext) {
    flow.run(context)
        .subscribe(object : SingleObserver<FlowActivity<*>> {
            override fun onSuccess(f: FlowActivity<*>?) {
                //executing next flow
                execute(f as FlowActivity<AcFlowContext>, context)
            }
    
            override fun onSubscribe(d: Disposable?) {
                //some action when flow subscribed
            }
    
            override fun onError(e: Throwable?) {
                //Handle error state
                e?.printStackTrace()
            }
    })
}
```
We able to do many complicated action on a flow because of `Rx` power. for example 
we can set `scheduler` of flows
```kotlin
flow.run(context)
    .subscribeOn(Scheduler.from(ioExecutor))
    .observeOn(Scheduler.from(uiExecute))
    .subscribe()
```