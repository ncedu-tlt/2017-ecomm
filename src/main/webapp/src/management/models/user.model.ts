import RoleModel from "./role.model";
export default class UserModel {
    id: number;
    roleId: number;
    firstName: string;
    roleName: string;
    email: string;
    registrationDate: string;
    phone: string;
    lastName: string;
    userAvatar: string;
    password: string;
    role: RoleModel;
}