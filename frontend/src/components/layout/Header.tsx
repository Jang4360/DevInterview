"use client";

import React from "react";
import { FiUser } from "react-icons/fi";
import Link from "next/link";

export default function Header() {
  return (
    <header className="flex items-center justify-between bg-[#212121] text-white px-6 py-4 border-b border-gray-700">
      <Link href="/review">
        <h1 className="text-xl font-bold absolute left-1/2 transform -translate-x+10/2 cursor-pointer">
          DevInterview
        </h1>
      </Link>

      {/* 우측 정렬 - Create 버튼 + User 아이콘 */}
      <div className="flex items-center space-x-4">
        <Link href="/create">
          <button className="border px-3 py-1 rounded">Create</button>
        </Link>
        <Link href="/my">
          <FiUser size={20} className="cursor-pointer" />
        </Link>
      </div>
    </header>
  );
}
