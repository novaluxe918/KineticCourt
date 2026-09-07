

document.addEventListener("DOMContentLoaded", function () {

    const modal =
        document.getElementById("detailHistoryModal");

    const detailButtons =
        document.querySelectorAll(".btn-detail");

    const closeButton =
        document.getElementById("btnCloseDetail");

    const closeButton2 =
        document.getElementById("btnCloseDetail2");


    // Mở modal
    detailButtons.forEach(button => {

        button.addEventListener("click", function () {

            modal.classList.remove("hidden");

            modal.classList.add("flex");

        });

    });


    // Hàm đóng modal
    function closeModal() {

        modal.classList.add("hidden");

        modal.classList.remove("flex");

    }


    // Đóng bằng nút X
    closeButton.addEventListener("click", closeModal);


    // Đóng bằng nút Đóng
    closeButton2.addEventListener("click", closeModal);


    // Click ra ngoài modal
    modal.addEventListener("click", function (event) {

        if (event.target === modal) {

            closeModal();

        }

    });

});
