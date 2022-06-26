function getJusho(insertId, instanceModal, postCodeVal, postCodeId, jushoKanjiId, jushoKanaId) {
    //モーダル内の住所リスト、エラーメッセージをクリア
    $(insertId).find('.jusholist').empty();
    $(insertId).find('.modal-error-message').empty();

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
        //住所1件毎に、イベント設定とラジオボタン要素生成
        data["jushoList"].forEach(function(item){
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
            $(insertId).find('.jusholist').append(radioInput);
        });
        //イベント設定。住所が選択されたら、モーダルをクローズする。※radioの選択が見えるように、遅延してクローズ。
        $(insertId).find('.jusholist input').each(function(index, element){
            $(element).on('change', function(){
                setTimeout(() => {instanceModal.close()}, 200);
            });
        });
    }).fail(function(data){
        if (data['status'] == 400) {
            //単項目チェックエラーのハンドリング
            if (data['responseJSON']['errorMessage']) {
                console.log(data['responseJSON']['errorMessage']);
                $(insertId).find('.modal-error-message').append('<p>'+data['responseJSON']['errorMessage']+'</p>');
            } else if (data['responseJSON']['errors'] && data['responseJSON']['errors'][0]['defaultMessage']) {
                console.log(data['responseJSON']['errors'][0]['field']);
                console.log(data['responseJSON']['errors'][0]['defaultMessage']);
                $(insertId).find('.modal-error-message').append('<p>'+data['responseJSON']['errors'][0]['defaultMessage']+'</p>');
            } else {
                console.log(data);
                window.location.href = '/';
            }
        }
        if (data['status'] >= 500 || data['status']  == 0) {
            //システムエラー時はトップへ遷移
            window.location.href = '/';
        }
    });
}

$(document).ready(function(){
    $('.jusho-trigger').each(function(index, triggerElement){
        const insertId = '#'+$(triggerElement).data('insertId');
        const optionsModal = {
            onCloseEnd: function(){
                //モーダルクローズ時に、モーダル内の住所リスト、郵便番号、エラーメッセージをクリア。
                $(insertId).find('.jusholist').empty();
                $(insertId).find('.postCode-modal').val('');
                $(insertId).find('.modal-error-message').empty();
            }
        }
        //モーダルインスタンス構築
        const modalElm = $(insertId).find('.modal')[0];
        const instanceModal = M.Modal.init(modalElm, optionsModal);

        //個別画面側のIDを取得
        const postCodeId = '#'+$(triggerElement).data('postCodeId');
        const jushoKanjiId = '#'+$(triggerElement).data('jushoKanjiId');
        const jushoKanaId = '#'+$(triggerElement).data('jushoKanaId');

        //モーダル起動ボタン
        $(triggerElement).on('click', function(){
            $(insertId).find('.postCode-modal').val($(postCodeId).val())
            instanceModal.open();
        });

        //モーダル内の検索ボタン
        $(insertId).find('.modal-search').on('click', function(){
            getJusho(insertId, instanceModal, $(insertId).find('.postCode-modal').val(), postCodeId, jushoKanjiId, jushoKanaId);
        });
    });
});
