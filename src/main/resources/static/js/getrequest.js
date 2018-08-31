$( document ).ready(function() {

    var url = window.location;

    $("#btnId").click(function(event){
        event.preventDefault();

        // Open Bootstrap Modal
        openModel();
        // Get data from Server
        ajaxGet();
    })

    // Open Bootstrap Modal
    function openModel(){
        $("#modalId").modal('show');
    }

    // DO GET
    function ajaxGet(){
        $.ajax({
            type : "GET",
            url : url + "/quotes/testQuote",
            success: function(data){
                // fill data to Modal Body
                fillData(data);
            },
            error : function(e) {
                fillData(null);
            }
        });
    }

    function fillData(data){
        if(data!=null){
            $(".modal-body #quote").text(data);
        }else{
            $(".modal-body #quote").text("Can Not Get Data from Server!");
        }
    }
})