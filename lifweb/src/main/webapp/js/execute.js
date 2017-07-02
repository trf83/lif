function executar(){
	var data = { 
				 action: "controlador.recuperar(pessoa)",
	    		 pessoa: JSON.stringify(form2object('cadastro', '.', true))
			   };	
	
	$.post("lif/json", data, sucesso);	
	
	var options = { 
		    uploadProgress: function(event, position, total, percentComplete) { }
	};	
	
	$('#upload').ajaxForm(options);
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
