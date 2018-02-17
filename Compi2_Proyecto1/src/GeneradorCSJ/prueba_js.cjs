funcion prueba(a,b,c){
	si(a > b && c*9 >= 5){
		dIMV a : a{5};
	}sino{
		para(a:10;a > 20;++){
			Dimv b : 'True';
		}
	}
}

funcion prueba2(ab,cd){
	Dimv p : Documento.Obtener("Tabla1").Setelemento("encabezado",a);
	Imprimir("comida" + "cabeza");
	
	Mensaje(a.conteo);
	Dimv b : arreglo.atexto();
	
	Dimv doc : Documento.Obtener("label");
	
	doc.Observador("listo",funcion(){
		Dimv abc : 'true';
	});
}

Dimv a : 19 + comida(4,comida);

funcion comida(a,b){

	retornAR a + b;
}

funcion eleccion(a){
	Dimv b : a;
	SELECCIONA (a){
		caso 6: 
			Dimv aaa : caer();
		caso 5:
			detener;
		defecto:
			a : nada;
			detener;
	}
}