import os

def rename_images(folder_path):
    # 지정한 폴더에서 파일 목록을 가져옵니다.
    file_list = os.listdir(folder_path)
    print(sorted(file_list))
    # 넘버링을 위한 카운터 초기화
    count = 264

    for i in range(len(file_list)) :
        # 파일의 전체 경로 생성
        old_filepath = os.path.join(folder_path, f"{i+1}번.png")

        # 새로운 파일 경로 생성
        new_filepath = os.path.join(folder_path, f"{count}번.png")

        # 파일 이름 변경
        os.rename(old_filepath, new_filepath)

        # 카운터 증가
        count += 1

if __name__ == "__main__":
    # 특정 폴더 경로 지정
    folder_path = "./src/Main/resource/파워"

    # 함수 호출
    rename_images(folder_path)
