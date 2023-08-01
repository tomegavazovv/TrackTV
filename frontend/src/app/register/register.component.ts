import {Component, ViewEncapsulation, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {RegisterService} from '../register.service';
import {Router} from "@angular/router";

@Component({
    selector: 'app-sign-up',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
    showPasswordMismatchError = false;
    signUpForm!: FormGroup;

    constructor(private formBuilder: FormBuilder,
                private service: RegisterService,
                private router: Router) {
    }

    ngOnInit() {
        this.signUpForm = this.formBuilder.group({
            username: ['', Validators.required],
            email: ['', [Validators.required, Validators.email]],
            password: ['', [Validators.required, Validators.minLength(6)]],
            c_password: ['', Validators.required]
        });
    }

    signUp(): void {
        if (this.signUpForm.controls['password'].value !== this.signUpForm.controls['c_password'].value) {
            this.showPasswordMismatchError = true;
            return;
        }

        this.showPasswordMismatchError = false;

        const registerData = {
            username: this.signUpForm.controls['username'].value,
            password: this.signUpForm.controls['password'].value,
            email: this.signUpForm.controls['email'].value
        };

        this.service.signup(registerData).subscribe({
            next: (response: any) => {
                this.router.navigate(['/home']);
            },
            error: (error) => {
                console.error('Registration failed:', error);
            }
        });
    }
}
