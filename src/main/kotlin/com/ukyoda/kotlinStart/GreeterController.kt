package com.ukyoda.kotlinStart

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

// インジェクションにはField InjectionやSetter Injectionもあるが、
// 基本的にはConstructor Injectionを使ったほうがいいとのこと
@RestController
@RequestMapping("greeter") // constructor injection
class GreeterController(private val greeter: Greeter) {
    // Field injectionはオブジェクト生成時に初期化されるのではなくて、後からインジェクションされるのでlateinit varにしておくのが良い
    // lateinit varは、初期化時はnullだけど、代入後は再代入不可になる変数を宣言できる
    @Autowired
    private lateinit var greeterWired: Greeter // Field injection

    // Setter injectionは独自のセッターを定義できるが、反面lateinitが使えない。
    // もしかすると、使えるけど使わんみたいな機能な気がする
    private var greeterSetter: Greeter? = null // Setter injection
        @Autowired
        set(value) {
            field = value
        }

    @GetMapping("/hello")
    fun hello(@RequestParam("name") name: String): HelloResponse {
        return HelloResponse("Hello $name");
    }

    @GetMapping("/hello/{name}")
    fun helloPathValue(@PathVariable("name") name: String): HelloResponse {
        return HelloResponse(name)
    }

    @GetMapping("/hello/byservice/{name}")
    fun helloByService(@PathVariable("name") name: String): HelloResponse {
        val message = greeter.sayHello(name)
        return HelloResponse(message)
    }

    @GetMapping("/hello/byservice2/{name}")
    fun helloByService2(@PathVariable("name") name: String): HelloResponse {
        val message = greeterWired.sayHello(name)
        return HelloResponse(message)
    }

}

data class HelloResponse(val message: String)