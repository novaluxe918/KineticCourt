
console.log("CHANGE DATE JS LOADED");
function changeDate(date) {

    if (!date) {
        return;
    }

    const urlParams = new URLSearchParams(window.location.search);
    const facilityId = urlParams.get("facilityId");

    window.location.href =
        "/book?facilityId=" + facilityId + "&date=" + date;
}