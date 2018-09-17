# KotlinDemo
纯Kotlin的demo【mvp+Retrofit+Rxjava】

<<------------------------------------MVP------------------------------------>>

V:
    interface IView

        abstract class BaseMvpActivity<T : IPresenter<*>> : IView

        abstract class BaseMvpFragment<T : IPresenter<*>> : IView




P:
    interface IPresenter<T>

        abstract class BasePresenter<T : IView> : IPresenter<T>


<<------------------------------------MVP------------------------------------>>
