var http=(function(){
  var _user='http://localhost:8080/user';
  var _topics='http://localhost:8080/topics';
  var _comment='http://localhost:8080/topics/detail';
  var login=function(){

  }
  var logon=function(name,password,callback){
    $.post(_user+"/logon",{name:name,password:password},function(data,status){
      callback(data,status);
    });
  }
  var login=function(name,password,callback){
    $.post(_user+"/login",{name:name,password:password},function(data,status){
      callback(data,status);

    });
  }
  var postTopic=function(poster,content,callback){
    var date=new Date();
    $.post(_topics,{title:"title",content:content,poster:poster,time:date.getTime()},function(data,status){
      callback(data,status);

    });
  }
  var reviseTopic=function (topid,content,callback) {
    $.post(_topics,{id:topid,content:content},function(data,status){
      callback(data,status);
    });
  }
  var getTopics=function(callback){
    $.getJSON(_topics,function(result){
          callback(result);
        });
  }
  var getTopic=function(name,callback){
    $.getJSON(_topics+'/'+name,function(result){
            // $.each(result, function(i, field){
            //     alert(field);
            // });
            callback(result);
        });
  }
  var postComment=function(commenter,content,topid,callback){
    var date=new Date();
    $.post(_comment+'/'+topid,{topid:topid,content:content,commenter:commenter,time:date.getTime()},function(data,status){
      callback(data,status);
    });
  }
  var getComments=function(id,callback){
    $.getJSON(_comment+'/'+id,function(result){
            // $.each(result, function(i, field){
            //     alert(field);
            // });
            callback(result);
        });
  }
  return{
    login:login,
    logon:logon,
    postTopic:postTopic,
    getTopics:getTopics,
    getTopic:getTopic,
    postComment:postComment,
    getComments:getComments,
    reviseTopic:reviseTopic
  }
})()
