package com.ukyoda.kotlinStart

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("hello")
class HelloController {
    @GetMapping("/world")
    fun world(model: Model): String {
        model.addAttribute("message", "Hello World!!");
        // https://qiita.com/tag1216/items/3680b92cf96eb5a170f0
        // * 戻り値がString: Templateファイルのパスっぽい
        // * 戻り値がModelAndView: Viewに渡したい情報を一緒に返すことができる
        // * 戻り値が`controller:method`の場合、他のコントローラのメソッドに遷移する
        // * 戻り値が`redirect:xxxx`の場合、xxxxにリダイレクトする （外部URLを指定することも可能）
        // * メソッドに@ResponseBodyをつけている: 戻り値で直接レスポンスのコンテンツを返却可能
        return "index";
    }

    @GetMapping("/rawdata")
    @ResponseBody
    fun rawData(): String {
        return "text content";
    }

    @GetMapping() // @GetMapping("/")だと、`/hello`でのアクセスに対応できないっぽい
    fun index(): String {
        return "redirect:/hello/world";
    }
}