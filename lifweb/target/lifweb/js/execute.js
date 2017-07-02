function executar(){
	var data = { 
				 action: "controlador.recuperar(pessoa)",
	    		 pessoa: JSON.stringify(form2object('cadastro', '.', true))
			   };	
	
	$.post("lif/json", data, sucesso);	
	
	var options = { 
		    uploadProgress:    function(event, position, total, percentComplete ) { 
    		  $('#progress_bar .ui-progress').animateProgress(percentComplete);		    		  
		    } 
	}; 
	
	$('#upload').ajaxForm(options);
	
	/*$.ajax({
		  type: "POST",
		  url: "/lifweb/upload",
		  data: {file: $('#file').val()},
		  contentType: "multipart/form-data",
		  sucess: function(data){			
		  		
		  }
		});*/
}



function sucesso(data){
	$('#resultado').html("<span>sucesso</span>");
}

function sucessoUpload(data){
	$('#resultado').html("<span>sucesso</span>");
}

function exibir(seletor, url){
	$(seletor).load(url);
}
