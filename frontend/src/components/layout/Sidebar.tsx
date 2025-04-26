"use client";

import React, { useState } from "react";
import Link from "next/link";

export default function Sidebar() {
  const [todayReviews, setTodayReviews] = useState([
    { id: 1, question: "ERD 설계" },
    { id: 2, question: "트랜잭션 ACID 원칙 준수" },
  ]); // 더미 데이터

  // 이후에 여기에 axios 넣으면 됨.

  return (
    <div className="w-60 bg-[#171717] text-white flex flex-col justify-between h-full p-4">
      <div>
        <div className="flex items-center justify-between px-10 py-0">
          {/* Today Review 글자 */}
          <h2 className="text-white text-lg font-bold">Today Review</h2>
        </div>

        <ul className="space-y-3">
          {todayReviews.length > 0 ? (
            todayReviews.map((review) => (
              <li
                key={review.id}
                className="text-sm hover:underline cursor-pointer"
              >
                <Link href={`/review/${review.id}?fromSidebar=true`}>
                  {review.question}
                </Link>
              </li>
            ))
          ) : (
            <li className="text-sm text-gray-400">
              오늘 복습할 질문이 없습니다.
            </li>
          )}
        </ul>
      </div>

      <div className="text-sm text-white opacity-50 mt-6">ⓒ DevInterview</div>
    </div>
  );
}
