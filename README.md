# KotlinDemo
纯Kotlin的demo【mvp+Retrofit+Rxjava】

<<------------------------------------MVP------------------------------------>>

V:
    interface IBaseView

        abstract class BaseMvpActivity<T : IBasePresenter<*>> : IBaseView

        abstract class BaseMvpFragment<T : IBasePresenter<*>> : IBaseView




P:
    interface IBasePresenter<T>

        abstract class BasePresenter<T : IBaseView> : IBasePresenter<T>


<<------------------------------------MVP------------------------------------>>
