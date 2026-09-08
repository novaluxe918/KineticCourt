function openBookingDetailModal(button) {

    let id = button.dataset.id;

    console.log("Booking ID:", id);

    fetch("/booking/detail/" + id)

        .then(response => response.json())

        .then(data => {

            console.log("Booking:", data);


            // Trạng thái
            document.getElementById("modalStatus").innerText =
                data.status ?? "-";


            // Ngày đặt
            document.getElementById("modalDate").innerText =
                data.booking_date ?? "-";


            // Tổng tiền
            document.getElementById("modalTotal").innerText =
                formatMoney(data.total);


            // =========================
            // Thông tin sân
            // =========================

            if (
                data.bookingDetail &&
                data.bookingDetail.length > 0
            ) {

                let detail = data.bookingDetail[0];

                let scheduleDetails =
                    detail.scheduleDetails;

                if (scheduleDetails) {

                    let schedule =
                        scheduleDetails.schedule;

                    let court =
                        schedule?.court;

                    let facility =
                        court?.facility;


                    // Tên CLB
                    document.getElementById("modalClubName").innerText =
                        facility?.name_facility ?? "-";


                    // Địa chỉ
                    document.getElementById("modalAddress").innerText =
                        facility?.address ?? "-";


                    // Tên sân
                    document.getElementById("modalCourtName").innerText =
                        court?.name_court ?? "-";


                    // Thời gian
                    let start =
                        scheduleDetails.time_start ?? "";

                    let end =
                        scheduleDetails.time_end ?? "";

                    document.getElementById("modalTime").innerText =
                        start + " - " + end;
                }
            }


            // =========================
            // Dịch vụ
            // =========================

            let serviceContainer =
                document.getElementById("modalServices");

            serviceContainer.innerHTML = "";


            if (
                data.bookingService &&
                data.bookingService.length > 0
            ) {

                data.bookingService.forEach(item => {

                    let li = document.createElement("li");

                    li.className =
                        "flex justify-between items-center py-1";


                    let serviceName =
                        item.service?.title ?? "Dịch vụ";

                    let quantity =
                        item.quantity ?? 0;

                    let price =
                        item.price ?? 0;


                    li.innerHTML = `
                        <span>
                            ${quantity}x ${serviceName}
                        </span>

                        <span>
                            ${formatMoney(price)}
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


            // =========================
            // Mở modal
            // =========================

            document.getElementById("detailHistoryModal")
                .classList.remove("hidden");

            document.getElementById("detailHistoryModal")
                .classList.add("flex");

        })

        .catch(error => {

            console.log(error);

            alert("Không thể tải thông tin đặt sân.");

        });
}


function closeBookingDetailModal() {

    document.getElementById("detailHistoryModal")
        .classList.add("hidden");

    document.getElementById("detailHistoryModal")
        .classList.remove("flex");
}


function formatMoney(value) {

    if (value == null) {
        return "-";
    }

    return Number(value).toLocaleString("vi-VN") + "đ";
}