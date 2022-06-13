function jusho(btnName){
    console.log(btnName);
    $(document).ready(function(){
        //TODO モーダル処理の部品化。
        const optionsModal = {
            onCloseEnd: function(){
                //モーダルクローズ時に、モーダル内住所リストをクリア。
                $('#jusholist').empty();
            }
        }
        const modalElm = document.querySelector('.modal');
        const instanceModal = M.Modal.init(modalElm, optionsModal);

        //住所検索ボタンイベント設定。クリック後にAjax呼び出し。
        $('#modal-trigger').on('click', function(){
            $.ajax({
                url:'/getJusho',
                type:'GET',
                data: {
                    postCode: $('#postCode').val(),
                },
                dataType: 'json',
                timeout: 3000,
                xhrFields: {
                    withCredentials: true
                },
            }).done(function(data){
                //住所1件毎に、イベント設定とラジオボタン要素生成。
    	        data.forEach(function(item){
    	            const postCode = item['postCode'];
    	            const jushoKanji = item['jushoKanji'];
    	            const jushoKana = item['jushoKana'];
    	            const radioInput = $('<p><label><input name="ju" type="radio"/><span>'+postCode+' '+jushoKanji+'</span></label></p>');
    	            //イベント設定。住所が選択されたら、選択された住所を画面本体へ反映する。
    	            $(radioInput).find('input').on('change', function(){
    	                $('#postCode').val(postCode);
    	                $('#jushoKanji').val(jushoKanji);
    	                $('#jushoKana').val(jushoKana);
    	            });
    	            $('#jusholist').append(radioInput);
    		    });
    		    //イベント設定。住所が選択されたら、モーダルをクローズする。※radioの選択が見えるように、遅延してクローズ。
    		    $('#jusholist input').each(function(index, element){
    		        $(element).on('change', function(){
    		            setTimeout(() => {instanceModal.close()}, 200);
    		        });
    		    });
    		    //モーダル画面を開く
    		    instanceModal.open();
            }).fail(function(data){
                console.log(data['statusText']);
                console.log(data['status']);
                console.log(data['responseText']);
            });
        });
    });
}
