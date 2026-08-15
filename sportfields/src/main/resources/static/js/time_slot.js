let slotIndex = document.querySelectorAll(".time-slot").length;

function addTimeSlot() {

    const container = document.getElementById("time-slots-container");
    const summarySlot = document.getElementById("summarySlot");

    const html = `
        <div class="time-slot grid grid-cols-12 gap-4 items-end p-4 bg-surface-container-lowest border border-outline-variant rounded-lg">

            <div class="col-span-4 space-y-2">
                <label>Giờ bắt đầu</label>
                <input
                    name="scheduleDetails[${slotIndex}].time_start"
                    class="w-full p-3 bg-white border border-outline-variant rounded-lg"
                    type="time"
                    required>
            </div>

            <div class="col-span-4 space-y-2">
                <label>Giờ kết thúc</label>
                <input
                    name="scheduleDetails[${slotIndex}].time_end"
                    class="w-full p-3 bg-white border border-outline-variant rounded-lg"
                    type="time"
                    required>
            </div>

            <div class="col-span-3 space-y-2">
                <label>Đơn giá (1h)</label>
                <input
                    name="scheduleDetails[${slotIndex}].price"
                    class="w-full price-input p-3 bg-white border border-outline-variant rounded-lg"
                    value="0"
                    type="number"
                    required>
            </div>

            <div class="col-span-1 flex justify-center">
                <button
                    class="delete-slot p-2 text-error hover:bg-error/10 rounded-lg"
                    type="button">
                    <span class="material-symbols-outlined">delete</span>
                </button>
            </div>

        </div>
    `;

    container.insertAdjacentHTML("beforeend", html);

    slotIndex++;

    summarySlot.innerText =
        document.querySelectorAll(".time-slot").length;

    tinhavg();
}


document.addEventListener("input", function (e) {

    if (e.target.classList.contains("price-input")) {
        tinhavg();
    }

});


document.addEventListener("click", function (e) {

    const deleteButton = e.target.closest(".delete-slot");

    if (!deleteButton) return;

    const slots = document.querySelectorAll(".time-slot");

    if (slots.length === 1) {
        alert("Phải có ít nhất một khung giờ.");
        return;
    }

    deleteButton.closest(".time-slot").remove();

    updateSlotIndex();

    document.getElementById("summarySlot").innerText =
        document.querySelectorAll(".time-slot").length;
});


function updateSlotIndex() {

    const slots = document.querySelectorAll(".time-slot");

    slots.forEach((slot, index) => {

        slot.querySelectorAll("input").forEach(input => {

            input.name = input.name.replace(
                /\[\d+\]/,
                `[${index}]`
            );

        });

    });

    slotIndex = slots.length;

    tinhavg();
}


function tinhavg() {

    const priceInputs =
        document.querySelectorAll(".price-input");

    let total = 0;
    let count = 0;

    priceInputs.forEach(input => {

        const price = Number(input.value);

        if (price > 0) {
            total += price;
            count++;
        }

    });

    const avg = count > 0 ? total / count : 0;

    document.getElementById("summaryPrice").innerText =
        avg.toLocaleString("vi-VN") + " VNĐ";
}