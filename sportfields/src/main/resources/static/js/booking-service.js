document.addEventListener("DOMContentLoaded", function () {

    const serviceItems = document.querySelectorAll(".service-item");

    const serviceFeeElement =
        document.getElementById("service-fee");

    const totalPriceElement =
        document.getElementById("total-price");

    const courtTotal =
        Number(totalPriceElement.dataset.courtTotal);

      const paymentButton =
             document.getElementById("btn-payment");



    serviceItems.forEach(item => {

        const plusButton =
            item.querySelector(".btn-plus");

        const minusButton =
            item.querySelector(".btn-minus");

        const quantityElement =
            item.querySelector(".service-quantity");

             const quantityInput =
                        item.querySelector(".service-quantity-input");


        let quantity = 0;


        // Nút +
        plusButton.addEventListener("click", function () {

            quantity++;

            quantityElement.textContent = quantity;
               quantityInput.value = quantity;

            updateTotal();
        });


        // Nút -
        minusButton.addEventListener("click", function () {

            if (quantity > 0) {
                quantity--;
            }

            quantityElement.textContent = quantity;
               quantityInput.value = quantity;

            updateTotal();
        });

    });


    function updateTotal() {

        let serviceTotal = 0;


        serviceItems.forEach(item => {

            const price =
                Number(item.dataset.price);

            const quantity =
                Number(
                    item.querySelector(".service-quantity")
                        .textContent
                );


            serviceTotal += price * quantity;

        });


        // Hiển thị tiền dịch vụ
        serviceFeeElement.textContent =
            serviceTotal.toLocaleString("vi-VN") + "đ";


        // Tổng tiền = tiền sân + tiền dịch vụ
        const total =
            courtTotal + serviceTotal;


        totalPriceElement.textContent =
            total.toLocaleString("vi-VN") + "đ";

    }

});