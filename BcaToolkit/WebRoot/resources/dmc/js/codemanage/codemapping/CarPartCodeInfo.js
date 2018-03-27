var path;
var addCodeTableDialog;
jQuery(function(){
  
  //���س�ʼ��ҳ��
  init();
  //������ҳ����Ϣ
  initBtn();

});
function init(){
  //ȫѡ����
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
  //��յ������div
  
  //clearDialogDiv();
  
  new Ajax.Updater('inputDiv',url,{
    method:'post',
    evalScripts:true,
    onComplete:function(res){
      //���ҳ���js�¼���
      //addInitEvent();
    //alert("hel")
    }
  });
  if(addCodeTableDialog == null ||!addCodeTableDialog.isExist()){
      addCodeTableDialog = new DialogEx('input',jQuery('#inputDiv'),{
        displayForm:1,
        isModal:false,
        title:'�½������Ϣ��',
        dialogType:2
        });
  }
  addCodeTableDialog.show({size:[660,400]});
  });
}
      

function deleteBtnEvent(){      
      //ɾ�������Ϣ�����
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
       alert("��ѡ����Ҫɾ���������Ϣ��");
     }else{
       if(confirm("�Ƿ���Ҫɾ��ѡ��������Ϣ����Ϣ��")){
         new Ajax.Updater('carPartCodeInfo',url,{
            method:'post',
            evalScripts:true,
            parameters: {"bo.data":data},
            onComplete: function(response) {
            
            }
          });
         alert("����ɾ���ɹ���");
          location = path + "/dmc/codemanage/car/CarPartCodeInfo.action";
       }
      
     }
    
    });
  }
  
  function updateBtnEvent(){   
        jQuery("#updateCarPartCode").click(function(){
          //alert("��������");
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
                alert("��ѡ����Ҫ���µ�����")
               }else if(size>1){
                alert("��ȷ����ѡ��ļ�¼��Ϊһ��");
               }else{
               var updateCarPartCodeDialog;
          //ֱ������һ���µ�dialog  --  �����������ص�div��ʾ�������������ǿյġ�
          if(updateCarPartCodeDialog==null ||!updateCarPartCodeDialog.isExist()){
          updateCarPartCodeDialog = new DialogEx('updateCarPartCodeInfoContent',jQuery("#updateCarPartCodeDiv"),{
          displayForm:1,
          isModal:true,
          title:'���������Ϣ����Ϣ',
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
      //���س�ʼ����ҳ����Ϣ ���¼�
        init();
      }
    
    });
    
    
  }); 
}
     
  function showDetail(id){
    var url = path + "/dmc/codemanage/car/CarPartCodeInfo!showDetail.action";
    //alert(url);
     var showHxhkDialog;
        //ֱ������һ���µ�dialog  --  �����������ص�div��ʾ�������������ǿյġ�
        if(showHxhkDialog==null ||!showHxhkDialog.isExist()){
          showHxhkDialog = new DialogEx('showCarPartCodeInfoContent',jQuery("#detailCarPartCodeDiv"),{
        displayForm:1,
        isModal:true,
        title:'�����Ϣ����ϸ��Ϣ',
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
  //alert("׼����������");
  
  new AjaxSubmit({
     url:url,
     form:'carPartCodeForm'
     }).targetSubmit();
 }); 
  }
)
  

