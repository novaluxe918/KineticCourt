Xây dựng hệ thống quản lý và đặt sân cầu lông trực tuyến tại Thành Phố Đà Nẵng
# Kinetic Court
# Giới thiệu

Kinetic Court là hệ thống quản lý và đặt sân cầu lông trực tuyến tại Thành phố Đà Nẵng, được xây dựng nhằm hỗ trợ người dùng dễ dàng tìm kiếm, xem lịch sân và đặt sân trực tuyến.

Hệ thống đồng thời cung cấp các chức năng quản lý dành cho quản trị viên (Admin) và chủ cơ sở (Owner), giúp quản lý cơ sở sân cầu lông, sân, lịch hoạt động, dịch vụ và thông tin đặt sân một cách thuận tiện.

Ứng dụng được phát triển theo mô hình Spring Boot MVC, sử dụng Thymeleaf để xây dựng giao diện phía máy chủ.

# Mục tiêu
- Hỗ trợ khách hàng tìm kiếm và lựa chọn sân cầu lông phù hợp.
- Cho phép khách hàng xem lịch sân theo ngày và khung giờ.
- Hỗ trợ đặt sân trực tuyến.
Cho phép chủ cơ sở quản lý cơ sở, sân, lịch hoạt động và dịch vụ.
Hỗ trợ Admin quản lý người dùng và các cơ sở trên hệ thống.
Giảm thiểu việc quản lý sân thủ công và hạn chế tình trạng trùng lịch đặt sân.
# Đối tượng sử dụng

Hệ thống gồm 3 nhóm người dùng chính:

# Khách hàng
Đăng ký và đăng nhập tài khoản.
Đăng nhập bằng Google.
Xem danh sách cơ sở sân cầu lông.
Xem thông tin chi tiết cơ sở.
Xem lịch sân theo ngày.
Xem các khung giờ còn trống.
Đặt sân trực tuyến.
Lựa chọn các dịch vụ đi kèm.
# Chủ cơ sở (Owner)
Đăng ký cơ sở sân cầu lông.
Quản lý thông tin cơ sở.
Quản lý danh sách sân.
Quản lý lịch hoạt động của sân.
Thiết lập giá sân theo từng khung giờ.
Quản lý dịch vụ tại cơ sở.
Theo dõi thông tin đặt sân.
# Quản trị viên (Admin)
Quản lý tài khoản người dùng.
Khóa/mở khóa tài khoản.
Quản lý cơ sở sân cầu lông.
Phê duyệt hoặc từ chối cơ sở do Owner đăng ký.
Quản lý dữ liệu của hệ thống.
# Công nghệ sử dụng
Backend
Java
Spring Boot
Spring MVC
Spring Data JPA / Hibernate
Spring Security
OAuth 2.0 / Google Login
Frontend
Thymeleaf
HTML5
CSS3
Tailwind CSS
JavaScript
Database
MySQL
Công cụ & dịch vụ
Maven
Git / GitHub
Cloudinary – lưu trữ hình ảnh
IntelliJ IDEA
