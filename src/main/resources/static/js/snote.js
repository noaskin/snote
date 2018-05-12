/**
 * 当前资源路径
 */
var path;
var index = 0;

$(function() {

    //


    var url = window.location.toString();
    var head = url.substr(0,url.indexOf("#",0));
    path = url.substr(url.indexOf("#",0)+1,url.length);

    // var path =getQueryString("path");
    // console.log(head);
    // console.log(path);
    // // path = changeURLArg(window.location.toString(),"path","你好");
    // // alert(path);
    // path = "";
    // window.location.href = head + "#" + path;
    // console.log(window.location.toString());

    $.get(path+"/README.md", function(md) {

        var testEditormdView2;

        testEditormdView2 = editormd.markdownToHTML("article-content", {
            htmlDecode      : "style,script,iframe",  // you can filter tags decode
            emoji           : true,
            markdown        : md,
            taskList        : true,
            tex             : true,  // 默认不解析
            flowChart       : true,  // 默认不解析
            sequenceDiagram : true,  // 默认不解析
        });
    });


});

/**
 * 修改URL参数
 */
function changeURLArg(url,arg,arg_val){
    var pattern=arg+'=([^&]*)';
    var replaceText=arg+'='+arg_val;
    if(url.match(pattern)){
        var tmp='/('+ arg+'=)([^&]*)/gi';
        tmp=url.replace(eval(tmp),replaceText);
        return tmp;
    }else{
        if(url.match('[\?]')){
            return url+'&'+replaceText;
        }else{
            return url+'?'+replaceText;
        }
    }
    return url+'\n'+arg+'\n'+arg_val;
}

/**
 * 获取URL参数，主要是路径
 * @param name
 * @returns {*}
 * @constructor
 */
function getQueryString(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.toString().match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}