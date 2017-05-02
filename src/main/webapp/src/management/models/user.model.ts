import RoleModel from "./role.model";
export default class UserModel {
    id: number;
    firstName: string;
    email: string;
    registrationDate: string;
    phone: string;
    lastName: string;
    userAvatar: string;
    password: string;
    role: RoleModel;
}