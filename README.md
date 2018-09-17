# KotlinDemo
纯Kotlin的demo【mvp+Retrofit+Rxjava+dagger】
![Image text](https://github.com/zhaoyuehai/KotlinDemo/blob/master/Screenshot_1.png)
![Image text](https://github.com/zhaoyuehai/KotlinDemo/blob/master/Screenshot_2.png)
![Image text](https://github.com/zhaoyuehai/KotlinDemo/blob/master/Screenshot_3.png)

<<------------------------------------MVP------------------------------------>>

V:
    interface IView

        abstract class BaseMvpActivity<T : IPresenter<*>> : IView

        abstract class BaseMvpFragment<T : IPresenter<*>> : IView




P:
    interface IPresenter<T>

        abstract class BasePresenter<T : IView> : IPresenter<T>


<<------------------------------------MVP------------------------------------>>