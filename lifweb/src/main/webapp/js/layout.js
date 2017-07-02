$(function() {
	template();	
	ajustarLaterais();
	  $('#progress_bar .ui-progress .ui-label').hide();
	  $('#progress_bar .ui-progress').css('width', '7%');
});

$(document).ajaxStop(function() {
	ajustarLaterais();
});

function ajustarLaterais() {
	var value = $("#conteudo-meio").height();
	$("#conteudo-esquerda").height(value);
	$("#conteudo-direita").height(value);
}

function template() {
	$("#topo").load("layout/header.html");
	$("#menu").load("layout/menu.html");
	$("#conteudo").load("layout/conteudo.html");
	$("#rodape").load("layout/footer.html");
}
