function jusho(btnName){
    console.log(btnName);
}

function getJusho(instanceModal, postCodeVal, postCodeId, jushoKanjiId, jushoKanaId) {
    $.ajax({
        url:'/getJusho',
        type:'GET',
        data: {
            postCode: postCodeVal,
        },
        dataType: 'json',
        timeout: 3000,
        xhrFields: {
            withCredentials: true
        },
    }).done(function(data){
        $('#jusholist').empty();
        //住所1件毎に、イベント設定とラジオボタン要素生成。
        data.forEach(function(item){
            const postCode = item['postCode'];
            const jushoKanji = item['jushoKanji'];
            const jushoKana = item['jushoKana'];
            const radioInput = $('<p><label><input name="ju" type="radio"/><span>'+postCode+' '+jushoKanji+'</span></label></p>');
            //イベント設定。住所が選択されたら、選択された住所を画面本体へ反映する。
            $(radioInput).find('input').on('change', function(){
                $(postCodeId).val(postCode);
                $(jushoKanjiId).val(jushoKanji);
                $(jushoKanaId).val(jushoKana);
            });
            $('#jusholist').append(radioInput);
        });
        //イベント設定。住所が選択されたら、モーダルをクローズする。※radioの選択が見えるように、遅延してクローズ。
        $('#jusholist input').each(function(index, element){
            $(element).on('change', function(){
                setTimeout(() => {instanceModal.close()}, 200);
            });
        });
    }).fail(function(data){
        // TODO エラー時の対応
        console.log(data['statusText']);
        console.log(data['status']);
        console.log(data['responseText']);
    });
}

function initJushoModal(triggerBtn, postCodeId, jushoKanjiId, jushoKanaId) {
    const optionsModal = {
        onCloseEnd: function(){
            //モーダルクローズ時に、モーダル内住所リストをクリア。
            $('#jusholist').empty();
        }
    }
    const modalElm = document.querySelector('.modal');
    const instanceModal = M.Modal.init(modalElm, optionsModal);

    //モーダル起動ボタン。住所検索ボタンイベント設定。クリック後にAjax呼び出し。
    $(triggerBtn).on('click', function(){
        const postCodeVal = $(postCodeId).val();
        if (postCodeVal == '') {
            instanceModal.open();
            return;
        }
        $('#postCode-modal').val(postCodeVal)
        getJusho(instanceModal, postCodeVal, postCodeId, jushoKanjiId, jushoKanaId);
        instanceModal.open();
    });

    //モーダル内の検索ボタン
    $('#modal-search').on('click', function(){
        getJusho(instanceModal, $('#postCode-modal').val(), postCodeId, jushoKanjiId, jushoKanaId);
    });
}