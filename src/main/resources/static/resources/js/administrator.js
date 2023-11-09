(function(){
    accountButtonListener();
}());

function accountButtonListener(){
    $('.account-menu').click(function(event){
        getAdministratorIndexHtml()
        getAdmministratorRows(1);
        addNewAdminButtonListener();
    });
};

function getAdministratorIndexHtml(){
    $('.main-content').html(
        `
        <a href="javascript:;" class="create-button new-admin">Add new Admin</a>
            <table class="table-content">
                <thead>
                    <tr>
                        <th>Action</th>
                        <th>Username</th>
                        <th>Job Title</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <a href="javascript:;" class="edit-button">edit</a>
                        </td>
                        <td class="username-row">Username</td>
                        <td class="jobTitle-row">Job title</td>
                    </tr>
                </tbody>
                <tfoot>
                <tr>
                <td colspan="3">
                    <div class="pagination">
                        <div><span class="currentPage"> </span> of <span class="totalPages"></span></div>
                        <div class="pages"></div>
                    </div>
                </td>
            </tr>
                </tfoot>
            </table>
        `
    ) 
}

function getAdmministratorRows(page){
    $.ajax({
        url:`http://localhost:7777/api/administrator/index?page=${page}`,
        success: function({content,totalPages}){
           let pageObject = {grid: content, totalPages : totalPages};
           renderGrid(pageObject,page)
           addPageNumberListener();
        }
    });
}

function addPageNumberListener(){
    $(".page-link").click(function(event){
       let page =  $(this).text();
       getAdmministratorRows(page);
    });
}

function renderGrid({grid, totalPages}, currentPage){
    $(".currentPage").text(currentPage);
    $(".totalPages").text(totalPages);
    let htmlPages = "";
    for(let i = 1; i<=totalPages; i++ ){
        let pages = `<div class="page-link">${i}</div>`
        htmlPages = htmlPages + pages 
    }
    $(".pages").html(htmlPages);

    let htmlString = "";
    for(let{username,jobTitle} of grid){
        let row = `<tr>
        <td>
            <a href="javascript:;" class="edit-button" data-id=${username}>edit</a>
        </td>
        <td class="username-row">${username}</td>
        <td class="jobTitle-row">${jobTitle}</td>
    </tr>`
    htmlString = htmlString + row;    
    }
    $('.table-content tbody').html(htmlString);
    editButtonListener();
}

function addNewAdminButtonListener(){
    addPopupHtml();

    $(".new-admin").click(function(event){
        event.preventDefault();
        $('.modal-layer').addClass('modal-layer--opened');
        $('.form-dialog').addClass('popup-dialog--opened');
    });
}

function addPopupHtml(){
    $('.modal-layer').html(`<div class="popup-dialog  form-dialog">
    <header>
        <h2>Add new Admin</h2>
    </header>
    <form>
        <table>
            <tbody>
            <tr>
                <td>Username</td>
                <td>
                    <input type="text" class="form-username">
                    <div class="validation-message" data-for="username"></div>

                </td>
            </tr>

            <tr>
                <td>Job Title</td>
                <td>
                    <input type="text" class="form-jobTitle">
                    <div class="validation-message" data-for="jobTitle"></div>

                </td>
            </tr>

    
            </tbody>
        </table>
        <a class="default-button save" href="javascript:;"> Save</a>
        <a class="default-button close" href="javascript:;"> Close</a>
    </form>

</div>

`)
closeButtonListener();
saveButtonListener();
}

function closeButtonListener(){
    $(".close").click(function(event){
        $('.modal-layer').removeClass('modal-layer--opened');
        $('.form-dialog').removeClass('popup-dialog--opened');
    });
}

function saveButtonListener(){
    $(".save").click(function(event){
        let dto = {
            username : $('.form-username').val(),
            jobTitle : $('.form-jobTitle').val()
        }

        $.ajax({
            method: 'POST',
            url:'http://localhost:7777/api/administrator/upsert',
            data: JSON.stringify(dto),
            contentType : 'application/json',
            success : function(response){
                $('.modal-layer').removeClass('modal-layer--opened');
                $('.form-dialog').removeClass('popup-dialog--opened');
                getAdministratorIndexHtml()
                getAdmministratorRows(1);
                addNewAdminButtonListener();
            },
        });
    });
}

function editButtonListener(){
    $('.edit-button').click(function(event){
        addHtmlEdit();
        $('.modal-layer').addClass('modal-layer--opened');
        $('.form-dialog').addClass('popup-dialog--opened');

    });
}

function addHtmlEdit(){
    $('.modal-layer').html(`<div class="popup-dialog  form-dialog">
    <header>
        <h2>Change Password</h2>
    </header>
    <form>
        <table>
            <tbody>
            <tr>
                <td>
                    <input type="text" placeholder = "Old Password" class="form-old-password">
                </td>
            </tr>

            <tr>
                <td>
                    <input type="text" placeholder = "New Password" class="form-new-password">
                </td>
            </tr>

            <tr>
                <td>
                    <input type="text" placeholder="Confirm Password" class="form-confirm-password">
                </td>
            </tr>

    
            </tbody>
        </table>
        <a class="default-button save-password" href="javascript:;"> Save</a>
        <a class="default-button close" href="javascript:;"> Close</a>
    </form>

</div>

`)
closeButtonListener();
// saveButtonListener();
}

 