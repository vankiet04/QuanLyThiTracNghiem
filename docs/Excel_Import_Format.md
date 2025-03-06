# Hướng Dẫn Định Dạng File Excel Để Import Câu Hỏi

## Định Dạng Chung
File Excel cần tuân thủ định dạng sau để có thể import thành công vào hệ thống:

1. Sử dụng file Excel định dạng `.xlsx` hoặc `.xls`
2. Dòng đầu tiên là dòng tiêu đề (sẽ bị bỏ qua khi import)
3. Mỗi dòng tiếp theo chứa thông tin của một câu hỏi

## Cấu Trúc Cột
Các cột trong file Excel cần được sắp xếp như sau:

1. **Cột A**: Nội dung câu hỏi (bắt buộc)
2. **Cột B**: Mã chủ đề (ID) (bắt buộc, phải là số nguyên tương ứng với ID chủ đề trong hệ thống)
3. **Cột C**: Độ khó (bắt buộc, chỉ chấp nhận một trong các giá trị: "Dễ", "Vừa", "Khó")
4. **Cột D**: Phương án A (bắt buộc)
5. **Cột E**: Phương án B (bắt buộc)
6. **Cột F**: Phương án C (tùy chọn)
7. **Cột G**: Phương án D (tùy chọn)
8. **Cột H**: Đáp án đúng (bắt buộc, ghi rõ phương án đúng, ví dụ: "A", "B", "C", "D")

## Ví Dụ
| Nội dung câu hỏi | Mã chủ đề | Độ khó | Phương án A | Phương án B | Phương án C | Phương án D | Đáp án đúng |
|------------------|----------|--------|------------|------------|------------|------------|-------------|
| Thủ đô của Việt Nam là gì? | 1 | Dễ | Hà Nội | TP HCM | Huế | Đà Nẵng | A |
| 2 + 2 = ? | 2 | Dễ | 3 | 4 | 5 | 6 | B |

## Lưu Ý Quan Trọng
- Các cột bắt buộc không được để trống
- Mã chủ đề phải tồn tại trong hệ thống
- Độ khó chỉ chấp nhận các giá trị: "Dễ", "Vừa", "Khó"
- Nếu một dòng có dữ liệu không hợp lệ, dòng đó sẽ bị bỏ qua và quá trình import vẫn tiếp tục với các dòng còn lại
