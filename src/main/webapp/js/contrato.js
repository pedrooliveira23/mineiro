/** *****Script da Página de Inspecao****** */	 
$(document).ready(function() {		 
    
    $.datepicker.setDefaults( $.datepicker.regional[ "pt-BR" ] );

  					$(".input-data").datepicker(); 
    
    $(".time").mask('Z0:00:00',{
        translation: {
          'Z': {
            pattern: /[0-2]/, optional: true
          }
        }
    });
    $(".bigint").mask('00000000000000000.00',{reverse:true});
    $(".time").on("blur", function() {
        var str = $(this).val().split(":");

        if(str.length==3){

                var nstr = "00"+(str[0]%24);
               str[0]=nstr.substr(nstr.length-2); 

            if(str[1]>59){
                var nstr = "00"+(str[1]%60);
               str[1]=nstr.substr(nstr.length-2); 
            }
            if(str[2]>59){
                var nstr = "00"+(str[2]%60);
               str[2]=nstr.substr(nstr.length-2); 
            }


            $(this).val( str[0]+":"+str[1]+":"+str[2] );
        }else{
            $(this).val("00:00:00");
        }
    });

  
  

    $.datepicker.setDefaults( $.datepicker.regional[ "pt-BR" ] );

    $(".time").timepicker({ 'timeFormat': 'H:i:s' });

    $('#btn-adicionar-diaNaoUtil').live('click', function() {
        $('#modal-diaNaoUtil').modal('show');
    });

});