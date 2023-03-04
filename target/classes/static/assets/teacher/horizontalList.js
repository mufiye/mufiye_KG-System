$(document).ready(function() {
    paging(1);
});

function paging(page){
    $.ajax({
        type : "get",// 请求方式
        url : "http://127.0.0.1:5055/horizontal.json",// 发送请求地址
        dataType : "json",
        data : {// 发送给数据库的数据
            flag : 'teacher_browse_RoomInfo',
            pageInt : page,
        },
        // 请求成功后的回调函数有两个参数
        success : function(data) {
            /*if(data.staticData){
                data={"pageNum": 1, "title": ["课程类别", "学时", "学分", "审核状态", "所属年份"], "output": [{"type": "毕业实习", "hour": "2周", "credit": "2", "id": "1", "status": "已审核", "year": "2014"}, {"type": "毕业设计", "hour": "16周", "credit": "6", "id": "1", "status": "审核未通过", "year": "2015"} ] };
            }*/
            var objs = eval(data); // 解析json对象
            var template = Handlebars.compile($("#seller-template").html());
            var titleTemplate = Handlebars.compile($("#title-template").html());

            //截止时间的helper
            Handlebars.registerHelper("changeDeadLine",function(value){
                if(null==value){
                    return "无限制";
                }else{
                    return value;
                }
            });

            // 把数据装入预编译后的页面
            $('#tableList').html(template(objs));
            $('#titleList').html(titleTemplate(objs));

            var pages = data.pageNum;
            var options = {
                currentPage: page,//当前页面
                numberOfPages: 5,//一页显示几个按钮（在ul里面生成5个li）
                totalPages:pages, //总页数
                itemTexts: function (type, page, current) {
                    switch (type) {
                        case "first":
                            return "首页";
                        case "prev":
                            return "上一页";
                        case "next":
                            return "下一页";
                        case "last":
                            return "尾页";
                        case "page":
                            return page;
                    }
                }
            }  ;
            $('#page_info').html("当前第" + page + "/" + pages + "页");
            $('#page_nav').bootstrapPaginator(options);
        },
        error : function(){
            //window.parent.location.href="../error.html";
        }
    });
}

function deleteItem(){
    var html=[];
    //$('#modalFoot').empty();
    $('#modal-header').html('提示信息');
    $('#modal-body').html('确认删除？');
    html.push('<button type="button" data-dismiss="modal" class="btn green">取消</button>');
    html.push('<button type="button" data-dismiss="modal" class="btn btn-primary">确认</button>');
    $('#modal-footer').html(html.join(""));
    $('#deleteModal').modal("show");
}

function changeItem(){

    $('#changeModal').modal("show");
}

function deleteBachelorGuide(id){
    $.ajax({
        type : "post",// 请求方式
        url : "../Delete.adub",// 发送请求地址
        dataType : "json",
        data : {// 发送给数据库的数据
            flag : 'deletRentInfo',
            id : id
        },
        // 请求成功后的回调函数有两个参数
        success : function(data) {
            if(data.num==1){
                var html=[];
                $('#modal-header').html('提示信息');
                $('#modal-footer').empty();
                $('#modal-body').html('成功删除1条数据');
                html.push('<button type="button" data-dismiss="modal" class="btn btn-primary" onclick="window.location.reload()">确认</button>');
                $('#modal-footer').html(html.join(""));
                $('#MyModal').modal("show");
            }
        },
        error : function(){
            window.parent.location.href="../error.html";
        }
    });
}