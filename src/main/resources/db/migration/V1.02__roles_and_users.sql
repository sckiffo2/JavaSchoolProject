INSERT INTO public.roles (role) VALUES ('ADMIN');
INSERT INTO public.roles (role) VALUES ('MANAGER');
INSERT INTO public.roles (role) VALUES ('USER');

INSERT INTO public.users (username, password, mail, active) VALUES ('admin', '{noop}admin', 'admin@admin.com', true);
INSERT INTO public.users (username, password, mail, active) VALUES ('manager', '{noop}manager', 'manager@manager.com', true);
INSERT INTO public.users (username, password, mail, active) VALUES ('user', '{noop}user', 'user@user.com', true);

INSERT INTO public.users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO public.users_roles (user_id, role_id) VALUES (1, 2);
INSERT INTO public.users_roles (user_id, role_id) VALUES (1, 3);
INSERT INTO public.users_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO public.users_roles (user_id, role_id) VALUES (2, 3);
INSERT INTO public.users_roles (user_id, role_id) VALUES (3, 3);