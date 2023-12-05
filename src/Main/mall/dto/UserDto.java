package Main.mall.dto;

import Main.mall.User;

public class UserDto {

    private String name;

    private String tel;

    private String address;

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static UserDto builder(User user){
        UserDto userDto = new UserDto();
        userDto.name = user.getName();
        userDto.tel = user.getTel();
        userDto.address = user.getAddress();
        return userDto;
    }
}
