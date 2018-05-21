/**
 * 当前资源路径
 */
var glogalPath = "/";
var index = 0;

var name;

String.prototype.replaceAll = function (FindText, RepText) {
    regExp = new RegExp(FindText, "g");
    return this.replace(regExp, RepText);
};

$(function () {

    // 获取URL中的参数路径
    var url = window.location.toString();
    // var head = url.substr(0, url.indexOf("#", 0));
    if (url.indexOf("#", 0) > 0) {
        glogalPath = url.substr(url.indexOf("#", 0) + 1, url.length);
    }
    //
    initDirectory();

});


/**
 * 更换文章
 * 1、Ajax请求文章内容，替换
 * 2、修改文章URL
 * 3、修改表头及选择项
 * 4、修改上一张和下一张
 * @param path 文章路径
 */
function changeArticle(path) {
    $("#" + getIdByPath(glogalPath)).removeClass("m-menu__item--active"); // 移除上一篇文章的样式
    if (document.getElementById(getIdByPath(path)) != null) { //如果目录存在
        $("#" + getIdByPath(path)).addClass("m-menu__item--active");
    } else {
        path = "/"; // 目录不存在，切换到根目录
        $("#D").addClass("m-menu__item--active");
    }
    changeMarkdown(path);
    glogalPath = path; //修改全局
}

/**
 * 修改markdown文本内容
 * @param path
 */
function changeMarkdown(path) {
    if (path.indexOf("/") == 0) {
        path = path.replace("/", "");
    }
    if (path.length > 0) {
        path += "/";
    }
    $("#article-content").text("");
    $.get(path + "README.md", function (md) {
        var markdownView;
        markdownView = editormd.markdownToHTML("article-content", {
            htmlDecode: "style,script,iframe",  // you can filter tags decode
            emoji: true,
            markdown: md,
            taskList: true,
            tex: true,  // 默认不解析
            flowChart: true,  // 默认不解析
            sequenceDiagram: true,  // 默认不解析
        });
        setContent(glogalPath);
    });
}

/**
 * 初始化目录
 */
function initDirectory() {
    $.get("/note/list", function (result) {
        name = result.name;
        var dirHtml = "<li class=\"m-menu__section m-menu__section--first\" id=\"" + initMap(result) + "\" >\n" +
            "<a onclick=\"changeArticle('/')\"" +
            "<h4 class=\"m-menu__section-text\">" +
            result.name +
            "</h4>\n" +
            "</a>" +
            "<i class=\"m-menu__section-icon flaticon-more-v3\"></i>\n" +
            "</li>";
        for (var i in result.children) {
            var data = result.children[i];
            if (data.children.length > 0) {
                dirHtml += "<li  id=\"" + initMap(data) + "\" class=\"m-menu__item  m-menu__item--submenu" +
                    " m-menu__item--open" +
                    " m-menu__item--expanded\"\n" +
                    "aria-haspopup=\"true\" m-menu-submenu-toggle=\"hover\">\n" +
                    "<a onclick=\"changeArticle('" + data.path + "')\" class=\"m-menu__link m-menu__toggle\">\n" +
                    "<i class=\"m-menu__link-icon flaticon-layers\"></i>\n" +
                    "<span class=\"m-menu__link-text\">\n" +
                    data.name +
                    "</span>\n" +
                    "<i class=\"m-menu__ver-arrow la la-angle-right\"></i>\n" +
                    "</a>\n" +
                    "<div class=\"m-menu__submenu \">\n" +
                    "<span class=\"m-menu__arrow\"></span>\n" +
                    "<ul class=\"m-menu__subnav\">";
                dirHtml += "<li class=\"m-menu__item  m-menu__item--parent\" aria-haspopup=\"true\">\n" +
                    "<span class=\"m-menu__link\">\n" +
                    "<span class=\"m-menu__link-text\">\n" +
                    data.name +
                    "</span>\n" +
                    "</span>\n" +
                    "</li>";
                for (var i in data.children) {
                    dirHtml += getChildDirectory(data.children[i]);
                }
                dirHtml += "</ul></div></li>";
            } else { // 叶子根节点
                dirHtml += "<li id=\"" + initMap(data) + "\" class=\"m-menu__item \" aria-haspopup=\"true\"" +
                    "m-menu-link-redirect=\"1\">\n" +
                    "<a onclick=\"changeArticle('" + data.path + "')\" class=\"m-menu__link \">\n" +
                    "<i class=\"m-menu__link-icon flaticon-share\"></i>\n" +
                    "<span class=\"m-menu__link-text\">\n" +
                    data.name +
                    "</span>\n" +
                    "</a>\n" +
                    "</li>";
            }

        }
        $("#directory_menu").html("");
        $("#directory_menu").html(dirHtml);
        changeArticle(glogalPath);
    });

}

var idRelPath = new Map();
var pathRelId = new Map();

function initMap(path) {
    idRelPath.set(path.id,path.path);
    pathRelId.set(path.path,path.id);
    console.log(idRelPath);
    return path.id;
}


/**
 * 根据ID获取Path
 * @param path
 * @returns {string | null}
 */
function getIdByPath(path) {
    return pathRelId.get(path);
}

/**
 * 根据ID获取Path路径
 * @param id
 */
function getPathById(id) {
    return idRelPath.get(id);
}

/**
 * 构建子目录
 * @param data
 * @param name
 */
function getChildDirectory(data, name) {
    var result = "";
    var size = data.children.length;
    if (size > 0) {
        result +=
            "<li  id=\"" + initMap(data) + "\" class=\"m-menu__item  m-menu__item--submenu\"" +
            " aria-haspopup=\"true\"\n" +
            "m-menu-submenu-toggle=\"hover\" m-menu-link-redirect=\"1\">\n" +
            "<a onclick=\"changeArticle('" + data.path + "')\" class=\"m-menu__link m-menu__toggle\">\n" +
            "<span class=\"m-menu__link-text\">\n" +
            data.name +
            "</span>\n" +
            "<i class=\"m-menu__ver-arrow la la-angle-right\"></i>\n" +
            "</a>\n" +
            "<div class=\"m-menu__submenu \">\n" +
            "<span class=\"m-menu__arrow\"></span>\n" +
            "<ul class=\"m-menu__subnav\">";
        for (var i in data.children) {
            result += this.getChildDirectory(data.children[i]);
        }
        result += "</ul></div></li>";
    } else { // 叶子节点
        result +=
            "<li  id=\"" + initMap(data) + "\" class=\"m-menu__item \" aria-haspopup=\"true\"" +
            " m-menu-link-redirect=\"1\">\n" +
            "<a onclick=\"changeArticle('" + data.path + "')\" class=\"m-menu__link \">\n" +
            "<span class=\"m-menu__link-text\">\n" +
            data.name +
            "</span>\n" +
            "</a>\n" +
            "</li>";
    }
    return result;
}

/**
 * 获取上一个目录
 * @param path
 */
function getPrevDirectory(path) {

}

/**
 * 获取下一个目录
 * @param path
 */
function getNextDirectory(path) {

}

/**
 * 设置文章标题等内容
 */
function setArticleTitle(path) {

}

/**
 * 设置导航等
 * @param path
 */
function setContent(path) {
    $("#content-list").html("");
    var id = getIdByPath(path);
    console.log(id);
    var words = id.substr(2,id.length).split('_');

    var listHtml = "<li class=\"m-nav__item m-nav__item--home\">\n" +
        "                                <a onclick=\"articleOnclick('D')\" class=\"m-nav__link m-nav__link--icon\">\n" +
        "                                    <i class=\"m-nav__link-icon la la-home\"></i>\n" +
        "                                </a>\n" +
        "                            </li>";
    var curId = "D";
    for (var i in words) {
        curId += "_" + words[i];
        listHtml += "<li class=\"m-nav__separator\">\n" +
            "                                -\n" +
            "                            </li>\n" +
            "                            <li class=\"m-nav__item\">\n" +
            "                                <a onclick=\"articleOnclick('" + curId + "')\"" +
            " class=\"m-nav__link\">\n" +
            "\t\t\t\t\t\t\t\t\t\t\t<span class=\"m-nav__link-text\">\n" +
            $("#"+curId).children("a").children("span").text() +
            "\t\t\t\t\t\t\t\t\t\t\t</span>\n" +
            "                                </a>\n" +
            "                            </li>";
        console.log(curId);
    }
    $("#content-list").html(listHtml);


    // $("#content-title").html("");
    // $("#content-title").html($("#"+curId).find("a").text());

}

function articleOnclick(id) {
    $("#" + id).children("a").click();
}










