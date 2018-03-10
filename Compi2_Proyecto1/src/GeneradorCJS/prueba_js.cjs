
Dimv c : {" USAC ",4,8,9%1};
c{1} : 5;
c{3} : " hola ";
Imprimir(C.atexto());

'Dimv jerson : b{0};
'mensaje(jerson);

'prueba({7,8,3}, {5," hola ",7*9/5});
funcion prueba(h,k){
    
    
    mensaje(h.atexto() + " " + k.atexto());
    Dimv u : 3;
    mensaje(u.atexto());
    Dimv a : 4;
    Dimv b : {5,6,7};
    b : {6,6,7,8};
    Dimv a{5};
    a : {5,6,7};
    b{4} : 5;
    Mensaje(a.atexto());
    
}

funcion fib(n)
{
    'Mensaje("nueva llamada");
    'Mensaje(n);
    si (n>2){
        'Mensaje("Es mayor a 1");
       retornar fib(n-1) + fib(n-2);
    }
    si (n == 1) {
        'Mensaje("Es igual a 1");
        retornar 1;
    }
    si (n == 0){
        'Mensaje("Es igual a 0");
        retornar 0;
    }
    si( n == 2){
        retornar 1;
    }
        'Mensaje("Es menor a 0");
        retornar -1; 
    
}



funcion prueba2(a,c){

}

funcion prueba2(ab,cd){
	Dimv p : Documento.Obtener("Tabla1").Setelemento("encabezado",a);
	Imprimir("comida" + "cabeza");
	
	Mensaje(a.conteo);
	Dimv b : arreglo.atexto();
	
	Dimv doc : Documento.Obtener("label");
	
	doc.Observador("listo",funcion(){
		Dimv abc : "true";
	});

        para(b:0;b<=29;++){
            
        }

        Selecciona(ab){
            caso 34:
            caso 56:
            defecto:
        }
}

'Dimv a : 19 + comida(4,comida);

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