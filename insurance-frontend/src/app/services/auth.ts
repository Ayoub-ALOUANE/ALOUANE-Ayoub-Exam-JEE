import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private host: string = "http://localhost:8082";
  public isAuthenticated: boolean = false;
  public roles: string[] = [];
  public username: string | undefined;
  public accessToken: string | undefined;

  constructor(private http: HttpClient, private router: Router) {
    this.loadToken();
  }

  login(credentials: any): Observable<any> {
    return this.http.post<any>(`${this.host}/login`, credentials).pipe(
      tap(data => {
        this.accessToken = data['access-token'];
        this.saveToken(this.accessToken!);
      })
    );
  }

  saveToken(token: string) {
    localStorage.setItem("token", token);
    this.decodeJWT(token);
  }

  decodeJWT(token: string) {
    try {
      const decoded: any = jwtDecode(token);
      this.username = decoded.sub;
      this.roles = decoded.roles;
      this.isAuthenticated = true;
    } catch (e) {
      console.error("Invalid token", e);
      this.logout();
    }
  }

  loadToken() {
    let token = localStorage.getItem("token");
    if (token) {
      this.accessToken = token;
      this.decodeJWT(token);
    }
  }

  logout() {
    this.isAuthenticated = false;
    this.roles = [];
    this.username = undefined;
    this.accessToken = undefined;
    localStorage.removeItem("token");
    this.router.navigateByUrl("/login");
  }

  hasRole(role: string): boolean {
    return this.roles.includes(role);
  }
}
