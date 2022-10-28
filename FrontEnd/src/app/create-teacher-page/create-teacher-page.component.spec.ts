import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateTeacherPageComponent } from './create-teacher-page.component';

describe('CreateUserPageComponent', () => {
  let component: CreateTeacherPageComponent;
  let fixture: ComponentFixture<CreateTeacherPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateTeacherPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateTeacherPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
