
document.addEventListener("DOMContentLoaded", function () {



    const bookingBlocks =
        document.querySelectorAll(".booking-block");

    const selectedSlotsContainer =
        document.getElementById("selected-slots-container");

    const noSelectionMsg =
        document.getElementById("no-selection-msg");

    const subtotalVal =
        document.getElementById("subtotal-val");

    const totalPriceVal =
        document.getElementById("total-price-val");

    const confirmButton =
        document.getElementById("btn-confirm");

        const facilityId =
            document.querySelector(
                'input[name="facilityId"]'
            ).value;



    // CLICK BOOKING


    bookingBlocks.forEach(block => {

        block.addEventListener("click", function () {

            // =================================
            // ĐANG CHỌN -> BỎ CHỌN
            // =================================

            if (this.classList.contains("selected")) {

                this.classList.remove(
                    "selected",
                    "bg-[#ff6b00]",
                    "border-[#ff6b00]",
                    "text-white"
                );

            }

            // =================================
            // CHƯA CHỌN -> CHỌN
            // =================================

            else {

                this.classList.add(
                    "selected",
                    "bg-[#ff6b00]",
                    "border-[#ff6b00]",
                    "text-white"
                );

            }


            // Cập nhật Aside
            updateSelectedSlots();

        });

    });




    function updateSelectedSlots() {

        // Xóa những slot cũ
        selectedSlotsContainer
            .querySelectorAll(".selected-slot")
            .forEach(slot => {
                slot.remove();
            });


        // Lấy tất cả booking đang được chọn
        const selectedBlocks =
            document.querySelectorAll(
                ".booking-block.selected"
            );




        if (selectedBlocks.length === 0) {

            noSelectionMsg.classList.remove("hidden");

            subtotalVal.textContent = "0đ";

            totalPriceVal.textContent = "0đ";

             confirmButton.classList.remove(
                  "bg-[#ff6b00]",
                  "hover:bg-[#e85f00]",
                  "cursor-pointer"
              );

              confirmButton.classList.add(
                  "bg-gray-400",
                  "opacity-60",
                  "cursor-not-allowed",
                  "pointer-events-none"
              );

              confirmButton.removeAttribute("href");

              return;
        }




        noSelectionMsg.classList.add("hidden");


        let totalPrice = 0;

        const selectedIds=[];
        selectedBlocks.forEach(block => {


            // Lấy dữ liệu từ HTML
            const court =
                block.dataset.court;
            const id = block.dataset.id;
            selectedIds.push(id);


            const start =
                block.dataset.start;

            const end =
                block.dataset.end;

            const price =
                Number(block.dataset.price);


            // Cộng tiền
            totalPrice += price;


            const slot =
                document.createElement("div");


            slot.className =
                "selected-slot " +
                "bg-surface-container-low " +
                "rounded-xl " +
                "p-4 " +
                "border " +
                "border-outline-variant";


            slot.innerHTML = `

                <div class="flex justify-between items-start gap-3">

                    <div>

                        <p class="font-bold text-on-surface">
                            ${court}
                        </p>

                        <p class="text-sm text-on-surface-variant mt-1">
                            ${start} - ${end}
                        </p>

                    </div>

                    <span class="font-bold text-primary whitespace-nowrap">
                        ${price.toLocaleString("vi-VN")}đ
                    </span>

                </div>

            `;


            selectedSlotsContainer.appendChild(slot);


        });
         console.log("Selected IDs:", selectedIds);



        subtotalVal.textContent =
            totalPrice.toLocaleString("vi-VN") + "đ";

        totalPriceVal.textContent =
            totalPrice.toLocaleString("vi-VN") + "đ";

          confirmButton.href =
                    "/booking/booking_detail" +
                    "?facilityId=" + facilityId +
                    "&selectedSlots=" + selectedIds.join(",");
        // Enable button
       confirmButton.classList.remove(
              "bg-gray-400",
              "opacity-60",
              "cursor-not-allowed",
              "pointer-events-none"
          );

          confirmButton.classList.add(
              "bg-[#ff6b00]",
              "hover:bg-[#e85f00]",
              "cursor-pointer"
          );

    }

});
