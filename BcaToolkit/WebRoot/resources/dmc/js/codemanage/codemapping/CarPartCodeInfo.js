var path;
var addCodeTableDialog;
jQuery(function(){
  
  //加载初始化页面
  init();
  //加载主页面信息
  initBtn();

});
function init(){
  //全选操作
  path=jQuery('#path').val();
  jQuery('#checkAll').click(function(){
   if(jQuery(this).attr("checked") == true)
      { 
       jQuery("input[name=selectMap]").each(function(){
      jQuery(this).attr("checked",true);
          });
         }
        else
         {
           jQuery("input[name=selectMap]").each(function(){
        jQuery(this).attr("checked",false);
         });
           }
      });
}
function initBtn(){
  addBtnEvent();
  deleteBtnEvent();
  updateBtnEvent();
  search();
}   

function addBtnEvent(){
  jQuery("#addCarPartCode").click(function(){
    
  var url = path + "/dmc/codemanage/car/CarPartCodeInfo!addInit.action";
  //清空弹出层的div
  
  //clearDialogDiv();
  
  new Ajax.Updater('inputDiv',url,{
    method:'post',
    evalScripts:true,
    onComplete:function(res){
      //添加页面的js事件绑定
      //addInitEvent();
    //alert("hel")
    }
  });
  if(addCodeTableDialog == null ||!addCodeTableDialog.isExist()){
      addCodeTableDialog = new DialogEx('input',jQuery('#inputDiv'),{
        displayForm:1,
        isModal:false,
        title:'新建零件信息表',
        dialogType:2
        });
  }
  addCodeTableDialog.show({size:[660,400]});
  });
}
      

function deleteBtnEvent(){      
      //删除零件信息表操作
      jQuery("#deleteCarPartCode").click(function(){
      var url = path + "/dmc/codemanage/car/CarPartCodeInfo!deleteCarPartCode.action";
      var data = "";
      var size = 0;
       jQuery("input[name=selectMap]").each(function(){
            if(jQuery(this).attr("checked") == true){
              data = data + jQuery(this).val()+",";
              size = size + 1;
            }
        });
       
       
     if(size < 1){
       alert("请选择需要删除的零件信息表");
     }else{
       if(confirm("是否真要删除选择的零件信息表信息？")){
         new Ajax.Updater('carPartCodeInfo',url,{
            method:'post',
            evalScripts:true,
            parameters: {"bo.data":data},
            onComplete: function(response) {
            
            }
          });
         alert("数据删除成功！");
          location = path + "/dmc/codemanage/car/CarPartCodeInfo.action";
       }
      
     }
    
    });
  }
  
  function updateBtnEvent(){   
        jQuery("#updateCarPartCode").click(function(){
          //alert("更新数据");
          var url = path + "/dmc/codemanage/car/CarPartCodeInfo!updateInit.action";
          //var url = "CarPartCodeInfo!updateInit.action";
          var data = "";
          var size = 0;
           jQuery("input[name=selectMap]").each(function(){
              if(jQuery(this).attr("checked") == true){
              data = data + jQuery(this).val()+",";
              size = size + 1;
                }
               });
               if(size<1){
                alert("请选择需要更新的数据")
               }else if(size>1){
                alert("请确定你选择的记录数为一条");
               }else{
               var updateCarPartCodeDialog;
          //直接生成一个新的dialog  --  作用是让隐藏的div显示出来，但内容是空的。
          if(updateCarPartCodeDialog==null ||!updateCarPartCodeDialog.isExist()){
          updateCarPartCodeDialog = new DialogEx('updateCarPartCodeInfoContent',jQuery("#updateCarPartCodeDiv"),{
          displayForm:1,
          isModal:true,
          title:'更新零件信息表信息',
          dialogType:2
            });
          }
          updateCarPartCodeDialog.show({size:[750,440]});
          new Ajax.Updater('updateCarPartCodeDiv',url,{
          method:'post',
          evalScripts:true,
          parameters: {"bo.data":data}
          });
          }
        });
      
  } 
      
function search(){
  jQuery('#searchCarPartCode').click(function(){
    var url = path + "/dmc/codemanage/car/CarPartCodeInfo!search.action";
    

    new Ajax.Updater('carPartCodeListDiv', url,{
      method: 'post',
      evalScripts: true,
      parameters: jQuery('#carPartCodeForm').serialize(),
      onComplete: function(response) {
      //加载初始化主页面信息 绑定事件
        init();
      }
    
    });
    
    
  }); 
}
     
  function showDetail(id){
    var url = path + "/dmc/codemanage/car/CarPartCodeInfo!showDetail.action";
    //alert(url);
     var showHxhkDialog;
        //直接生成一个新的dialog  --  作用是让隐藏的div显示出来，但内容是空的。
        if(showHxhkDialog==null ||!showHxhkDialog.isExist()){
          showHxhkDialog = new DialogEx('showCarPartCodeInfoContent',jQuery("#detailCarPartCodeDiv"),{
        displayForm:1,
        isModal:true,
        title:'零件信息表详细信息',
        dialogType:2
          });
        }
        showHxhkDialog.show({size:[750,440]});
        new Ajax.Updater('detailCarPartCodeDiv',url,{
        method:'post',
        evalScripts:true,
        parameters: {"bo.partId":id}
        });
  }
  
  jQuery(function(){
    jQuery('#exportCarPartCode').click(function(){
  var url = path + "/dmc/codemanage/car/exportCarPartCode!exportCarPartCodeInfo.action";
  //alert("准备导出数据");
  
  new AjaxSubmit({
     url:url,
     form:'carPartCodeForm'
     }).targetSubmit();
 }); 
  }
)
  

