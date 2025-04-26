"use client";

import React, { useState } from "react";
import Input from "@/components/common/Input";
import Button from "@/components/common/Button";
import { useRouter } from "next/navigation";
import Link from "next/link";

export default function SignupPage() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const router = useRouter();

  const handleSignup = () => {
    // TODO: 회원가입 API 연결 예정
    if (password !== confirmPassword) {
      alert("비밀번호가 일치하지 않습니다.");
      return;
    }

    // 임시 성공 처리
    alert("회원가입 완료!");
    router.push("/login");
  };

  return (
    <div className="flex justify-center items-center h-screen bg-[#212121]">
      <div className="bg-[#171717] p-8 rounded-lg w-full max-w-md text-white">
        <h1 className="text-2xl font-bold text-center mb-6">Dev Interview</h1>

        <Input
          value={name}
          onChange={(e) => setName(e.target.value)}
          placeholder="이름"
          className="mb-4 w-full bg-[#303030]"
        />
        <Input
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          placeholder="이메일"
          className="mb-4 w-full bg-[#303030]"
        />
        <Input
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          placeholder="비밀번호"
          type="password"
          className="mb-4 w-full bg-[#303030]"
        />
        <Input
          value={confirmPassword}
          onChange={(e) => setConfirmPassword(e.target.value)}
          placeholder="비밀번호 확인"
          type="password"
          className="mb-6 w-full bg-[#303030]"
        />

        <Button onClick={handleSignup} className="w-full bg-[#303030]">
          회원가입
        </Button>

        <p className="text-center text-gray-400 mt-4">
          이미 계정이 있으신가요?{" "}
          <Link href="/login" className="underline text-white">
            로그인
          </Link>
        </p>
      </div>
    </div>
  );
}
