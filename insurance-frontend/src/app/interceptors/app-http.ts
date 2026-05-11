import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth';

export const appHttpInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);
  if (authService.isAuthenticated && authService.accessToken) {
    const authReq = req.clone({
      headers: req.headers.set('Authorization', 'Bearer ' + authService.accessToken)
    });
    return next(authReq);
  }
  return next(req);
};
