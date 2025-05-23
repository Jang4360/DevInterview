"use client";

import React, { useState } from "react";
import { useRouter } from "next/router";

export default function LoginPage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const router = useRouter();

  const handleLogin = () => {
    // TODO: 로그인 처리
    if (email && password) {
      router.push("/review");
    } else {
      alert("이메일과 비밀번호를 입력하세요.");
    }
  };

  return (
    <div className="flex items-center justify-center h-screen bg-[#212121] text-white">
      <div className="w-full max-w-md bg-[#171717] p-8 rounded-lg shadow-lg">
        <h2 className="text-3xl font-bold mb-8 text-center">Dev Interview</h2>
        <input
          type="email"
          placeholder="이메일"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          className="w-full p-3 mb-4 bg-[#303030] text-white rounded placeholder-[#BBB5B5]"
        />
        <input
          type="password"
          placeholder="비밀번호"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          className="w-full p-3 mb-6 bg-[#303030] text-white rounded placeholder-[#BBB5B5]"
        />
        <button
          onClick={handleLogin}
          className="w-full bg-[#303030] p-3 rounded hover:bg-[#404040] mb-4"
        >
          로그인
        </button>
        <p
          className="text-center text-sm text-[#BBB5B5] cursor-pointer hover:underline"
          onClick={() => router.push("/signup")}
        >
          회원가입 하기
        </p>
      </div>
    </div>
  );
}
