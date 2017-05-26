import "rxjs/add/operator/switchMap";
import {Component, Input, OnInit} from "@angular/core";
import UserModel from "../../models/user.model";
import {UsersService} from "../../services/users.service";
import {Location} from "@angular/common";
import RoleModel from "../../models/role.model";
import {FormGroup} from "@angular/forms";
import {SemanticPopupComponent} from "ng-semantic";

declare const contextPath: string;

@Component({
    selector: 'nc-user-editor',
    templateUrl: 'user-editor.component.html',
    styleUrls: ['user-editor.component.css']
})

export class UserEditorComponent implements OnInit {

    @Input()
    userId: number;

    user: UserModel = new UserModel();
    roles: RoleModel[] = [];
    isSent: boolean = false;
    isError: boolean = false;
    contextPath = contextPath;
    file: File;
    fileSours: string = "";
    formData: FormData = new FormData;
    private altImageSource: string = contextPath + "/images/useravatars/unknownuser/unknownuser.png";
    private pathToImageUrl = "/image/";
    private imageUrl = `${contextPath}/uploadImage`;
    private passwordUrl = `${contextPath}/uploadPassword`;


    constructor(private usersService: UsersService,
                private location: Location) {
    }

    ngOnInit(): void {
        this.user.role = new RoleModel;
        this.usersService.getRoles().then(roles => this.roles = roles);
        if (this.userId) {
            this.usersService.getUser(this.userId).then(user => this.user = user);
        }
    }

    onSave(form: FormGroup, popup: SemanticPopupComponent, $event: any): void {
        if (this.userId) {
            this.saveImage();
            this.usersService.updateUser(this.user)
                .then(() => {
                    this.isSent = true;
                    this.isError = false;
                    this.location.back();
                }).catch(() => {
                this.isError = true;
                this.isSent = false;
                popup.show($event, {position: 'bottom left'});
            });
        } else {
            this.usersService.addUser(this.user)
                .then(() => {
                    this.isSent = true;
                    this.isError = false;
                    this.encryptPassword();
                    form.reset();
                    popup.show($event, {position: 'bottom left'});
                })
                .catch(() => {
                    this.isError = true;
                    this.isSent = false;
                    popup.show($event, {position: 'bottom left'});
                });
        }
    }

    encryptPassword(): void {
        this.formData.delete('userPassword');
        this.formData.append('userPassword', this.user.password);
        this.usersService.uploadToServlet(this.passwordUrl, this.formData);
    }

    saveImage(): void {
        if (this.file) {
            this.user.userAvatar = this.pathToImageUrl + this.file.name;
            this.usersService.uploadToServlet(this.imageUrl, this.formData);
        }
    }

    onCancel(): void {
        this.location.back();
    }

    onDelete(): void {
        if (this.userId) {
            this.usersService.deleteUser(this.userId).then(this.onCancel.bind(this));
        }
    }

    selectFile(event: any): void {
        let fileList: FileList = event.target.files;
        if (fileList.length > 0) {
            this.file = fileList[0];
            this.formData.delete('uploadFile');
            this.formData.append('uploadFile', this.file);
            this.previewImage(this.file);
        }
    }

    previewImage(file: any): void {
        this.readFile(file, (result: any) => {
            const img = document.createElement("img");
            img.src = result;
            this.drawImage(img, (imageJpeg: any) => {
                this.fileSours = imageJpeg;
            });
        });
    }

    readFile(file: any, callback: any): void {
        const reader = new FileReader();
        reader.onload = () => {
            callback(reader.result);
        };
        reader.readAsDataURL(file);
    }

    drawImage(img: any, callback: any) {
        return img.onload = () => {
            const canvas = document.createElement("canvas");
            canvas.width = img.width;
            canvas.height = img.height;
            canvas.getContext("2d").drawImage(img, 0, 0, img.width, img.height);
            callback(canvas.toDataURL('image/jpeg'));
        };
    }

    imageSourceError($event: any): void {
        $event.target.src = this.altImageSource;
    }

}