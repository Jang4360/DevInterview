import React from "react";

export default function Sidebar() {
  return (
    <div className="w-60 bg-[#171717] text-white flex flex-col justify-between h-full p-6">
      <div className="px-4 py-3">
        <h2 className="px-4 text-white text-lg font-bold">Today Review</h2>
        {/* 🔥 더 이상 버튼 없음 */}
      </div>
      <div className="text-sm text-white opacity-50">ⓒ DevInterview</div>
    </div>
  );
}
