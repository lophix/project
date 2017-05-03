<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/28
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hello</title>
    <script src="js/vue.js"></script>
</head>
<body>
    <h1>Hello, welcome!</h1>
    <div id="app">
        {{message}}
    </div>
    <div id="app-2">
        <span v-bind:title="message">
            鼠标悬停几秒钟查看此处动态绑定的提示信息!
        </span>
    </div>
    <div id="app-3">
        <p v-if="seen">现在你看到我了</p>
    </div>
    <div id="app-4">
        <ol>
            <li v-for="todo in todos">
                {{ todo.text }}
            </li>
        </ol>
    </div>
    <div id="app-5">
        <p>{{message}}</p>
        <button v-on:click="reverseMessage">逆转消息</button>
    </div>
    <div id="app-6">
        <p>{{message}}</p>
        <input v-model="message">
    </div>
    <div id="app-7">
        <ol>
            <todo-item v-for="item in groceryList" v-bind:todo="item"></todo-item>
        </ol>
    </div>

    <script>
        var app = new Vue({
            el : '#app',
            data : {
                message : 'Hello Vue!'
            }
        });

        var app2 = new Vue({
            el : '#app-2',
            data : {
                message : '页面加载于 ' + new Date()
            }
        });

        var app3 = new Vue({
            el : '#app-3',
            data : {
                seen : true
            }
        });

        var app4 = new Vue({
            el : '#app-4',
            data :{
                todos : [
                    {text: '学习 JavaScript'},
                    {text: '学习 Vue'},
                    {text: '赚他个一个亿'}
                ]
            }
        });

        var app5 = new Vue({
            el : '#app-5',
            data : {
                message : 'Hello Vue.js!'
            },
            methods : {
                reverseMessage : function () {
                    this.message = this.message.split('').reverse().join('')
                }
            }
        });

        var app6 = new Vue({
            el : '#app-6',
            data : {
                message : 'what the fuck'
            }
        });

        Vue.component('todo-item', {
            props : ['todo'],
            template : '<li>{{todo.text}}</li>'
        });

        var app7 = new Vue({
            el : '#app-7',
            data : {
                groceryList : [
                    { text : 'vegetable' },
                    { text : 'cheese'},
                    {text : 'whatever'}
                ]
            }
        })
</script>
</body>
</html>
