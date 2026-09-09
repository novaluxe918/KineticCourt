document.addEventListener("DOMContentLoaded", function () {


//click vao nut xem chi tiet
    document.querySelectorAll(".btn-detail").forEach(button => {

        button.addEventListener("click", function () {
            openBookingDetailModal(this);
        });

    });



    const btnClose =
        document.getElementById("btnCloseDetail");

    if (btnClose) {

        btnClose.addEventListener("click", function () {
            closeBookingDetailModal();
        });

    }




    const modal =
        document.getElementById("detailHistoryModal");

    if (modal) {

        modal.addEventListener("click", function (event) {

            if (event.target === modal) {
                closeBookingDetailModal();
            }

        });

    }

});



function openBookingDetailModal(button) {

    let id = button.dataset.id;

    console.log("Booking ID:", id);


    fetch("/booking/detail/" + id)

        .then(response => {

            console.log("HTTP Status:", response.status);
            console.log("URL:", response.url);

            if (!response.ok) {
                throw new Error("Không lấy được booking");
            }

            return response.json();

        })

        .then(data => {

            console.log("Booking:", data);




            document.getElementById("modalStatus").innerText =
                data.status ?? "-";


            document.getElementById("modalDate").innerText =
                data.booking_date ?? "-";

         let total = Number(data.total) || 0;

         document.getElementById("modalTotal").innerText =
             formatMoney(total);




            document.getElementById("modalClubName").innerText =
                data.name_facility ?? "-";


            document.getElementById("modalAddress").innerText =
                data.address ?? "-";



            document.getElementById("modalCourtName").innerText =
                data.name_court ?? "-";


            document.getElementById("modalTime").innerText =
                (data.time_start ?? "") +
                " - " +
                (data.time_end ?? "");



            let serviceContainer =
                document.getElementById("modalServices");


            serviceContainer.innerHTML = "";


            if (
                data.services &&
                data.services.length > 0
            ) {

                data.services.forEach(item => {

                    let li =
                        document.createElement("li");


                    li.className =
                        "flex justify-between items-center py-1";


                    li.innerHTML = `
                        <span class="font-body-md text-body-md text-on-background">
                            ${item.quantity ?? 0}x ${item.title ?? "Dịch vụ"}
                        </span>

                        <span class="font-body-md text-sm text-on-surface-variant">
                            ${formatMoney(item.price)}
                        </span>
                    `;


                    serviceContainer.appendChild(li);

                });

            } else {

                serviceContainer.innerHTML = `
                    <li class="text-sm text-on-surface-variant">
                        Không có dịch vụ đi kèm
                    </li>
                `;

            }


            // ========================================
            // Hiển thị modal
            // ========================================

            const modal =
                document.getElementById("detailHistoryModal");


            modal.classList.remove("hidden");

            modal.classList.add("flex");

        })

        .catch(error => {

            console.error("Lỗi:", error);

            alert("Không thể tải thông tin đặt sân.");

        });

}




function closeBookingDetailModal() {

    const modal =
        document.getElementById("detailHistoryModal");


    modal.classList.add("hidden");

    modal.classList.remove("flex");

}


function filterBookings(type){
const bookings = document.querySelectorAll(".booking-card");

    bookings.forEach(booking => {

        const status = booking.dataset.status;

        if (type === "all") {

            booking.style.display = "";

        } else if (type === "upcoming") {

            if (status === "PENDING") {
                booking.style.display = "";
            } else {
                booking.style.display = "none";
            }

        } else if (type === "completed") {

            if (status === "COMPLETED") {
                booking.style.display = "";
            } else {
                booking.style.display = "none";
            }
        }
    });


    // Đổi trạng thái nút đang chọn
    document.querySelectorAll(".flex button").forEach(button => {
        button.classList.remove("active-tab");
    });

    document.getElementById("btn-" + type)?.classList.add("active-tab");
}

function formatMoney(value) {

    if (value == null) {
        return "-";
    }


    return Number(value).toLocaleString("vi-VN") + "đ";

}